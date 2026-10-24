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
    version = 2,
    exportSchema = true
)
abstract class SaatanDatabase : RoomDatabase() {
    abstract fun journalDao(): DailyJournalDao

    companion object {
        const val DATABASE_NAME = "saatan_database.db"

        @Volatile
        private var INSTANCE: SaatanDatabase? = null

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
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
