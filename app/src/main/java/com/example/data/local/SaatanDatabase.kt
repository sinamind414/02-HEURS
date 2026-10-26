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
    version = 4,
    exportSchema = true
)
abstract class SaatanDatabase : RoomDatabase() {
    abstract fun journalDao(): DailyJournalDao

    companion object {
        const val DATABASE_NAME = "saatan_database.db"

        @Volatile
        private var INSTANCE: SaatanDatabase? = null

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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
