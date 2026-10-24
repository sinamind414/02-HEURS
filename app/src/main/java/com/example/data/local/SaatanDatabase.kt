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
        UserProfileEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class SaatanDatabase : RoomDatabase() {
    abstract fun journalDao(): DailyJournalDao

    companion object {
        const val DATABASE_NAME = "saatan_database.db"

        @Volatile
        private var INSTANCE: SaatanDatabase? = null

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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
