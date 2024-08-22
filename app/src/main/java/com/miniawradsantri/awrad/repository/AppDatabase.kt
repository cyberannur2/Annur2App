package com.miniawradsantri.awrad.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miniawradsantri.awrad.dao.AppDao
import com.miniawradsantri.awrad.utils.Converters
import com.miniawradsantri.awrad.entities.*
import com.miniawradsantri.awrad.utils.*

@Database(entities = [ArticleEntity::class, CategoryEntity::class, MediaEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "wordpress_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
