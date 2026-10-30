package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        DailyJournalEntity::class,
        UserProfileEntity::class,
        PrayerTimingsCacheEntity::class
    ],
    version = 7,
    exportSchema = true
)
abstract class SaatanDatabase : RoomDatabase() {
    abstract fun journalDao(): DailyJournalDao

    companion object {
        const val DATABASE_NAME = "saatan_database.db"

        @Volatile
        private var INSTANCE: SaatanDatabase? = null

        /**
         * v6 -> v7
         *  * Weekly Presence Report (slice 3, 30/10/2026): holds the gentle
         *    intention set from the weekly report, keyed to the week it was
         *    made in. Stored raw and echoed back only — never scored.
         */
        val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN weeklyIntentionText TEXT NOT NULL DEFAULT ''"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN weeklyIntentionWeekKey TEXT NOT NULL DEFAULT ''"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN weeklyIntentionAtMs INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        /**
         * v5 -> v6
         *  * Presence Rhythm (US repositioning, 29/10/2026): adds the US
         *    onboarding state (disclaimer ack + rhythm choice) and the
         *    `tradition_layer` flag that switches between the light
         *    "Presence Rhythm" skin ("off") and the original Dhawq skin
         *  * adds the daily Inner Weather columns to the journal — a raw
         *    label stored on device, never scored or aggregated (Serment #2)
         */
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN usOnboardingDone INTEGER NOT NULL DEFAULT 0"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN crisisAckAtMs INTEGER NOT NULL DEFAULT 0"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN usRhythm TEXT NOT NULL DEFAULT 'beginner'"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN traditionLayer TEXT NOT NULL DEFAULT 'off'"
                )
                db.execSQL(
                    "ALTER TABLE daily_journal ADD COLUMN moodCheckIn TEXT NOT NULL DEFAULT ''"
                )
                db.execSQL(
                    "ALTER TABLE daily_journal ADD COLUMN moodAfterPause TEXT NOT NULL DEFAULT ''"
                )
            }
        }

        /**
         * v4 -> v5
         *  * persists the last city/country used for prayer times so the app
         *    restores the user's own location at startup instead of falling
         *    back to Paris on every cold start.
         */
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN cityName TEXT NOT NULL DEFAULT 'Paris'"
                )
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN countryName TEXT NOT NULL DEFAULT 'France'"
                )
            }
        }

        /**
         * v3 -> v4
         *  * creates the `prayer_timings_cache` snapshot (single row) used by
         *    the per-prayer reminder receivers to re-arm from disk, offline
         *  * resets the Fajr / Dhuhr / Friday reminder flags to OFF: they had
         *    been stored as `true` since v1 while no scheduler ever read them.
         *    Now that the scheduling actually exists (P1-3), waking them up
         *    with their old values would silently start notifying every
         *    existing user up to three extra times a day — everyone chooses
         *    the toggles back on the settings.
         */
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `prayer_timings_cache` (" +
                        "`id` INTEGER NOT NULL, " +
                        "`city` TEXT NOT NULL, " +
                        "`country` TEXT NOT NULL, " +
                        "`timezoneId` TEXT NOT NULL, " +
                        "`utcOffsetSeconds` INTEGER NOT NULL, " +
                        "`dateIso` TEXT NOT NULL, " +
                        "`savedAtMillis` INTEGER NOT NULL, " +
                        "`fajr` TEXT NOT NULL, " +
                        "`dhuhr` TEXT NOT NULL, " +
                        "`asr` TEXT NOT NULL, " +
                        "`maghrib` TEXT NOT NULL, " +
                        "`isha` TEXT NOT NULL, " +
                        "PRIMARY KEY(`id`))"
                )
                db.execSQL(
                    "UPDATE user_profile SET reminderFajr = 0, reminderDhuhr = 0, reminderFriday = 0"
                )
            }
        }

        /**
         * v2 -> v3
         *  * adds the daily "taste of the prayer" answer (Dhawq cycle). The
         *    value is only ever shown for the current day — it is never
         *    aggregated into a score or a streak (Serment #2).
         */
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE daily_journal ADD COLUMN salahTaste TEXT NOT NULL DEFAULT ''"
                )
            }
        }

        /**
         * v1 -> v2
         *  * adds the persisted day / night theme flag
         *  * drops the `unlocked_challenges` table, which was created but never
         *    written to (the challenge list is derived from the journal)
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN isDayMode INTEGER NOT NULL DEFAULT 0"
                )
                db.execSQL("DROP TABLE IF EXISTS `unlocked_challenges`")
            }
        }

        fun getDatabase(context: Context): SaatanDatabase {
            // Double checked locking: the instance must be re-read *inside* the
            // synchronized block, otherwise two threads racing on the first
            // access can build two connection pools for the same file.
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SaatanDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
