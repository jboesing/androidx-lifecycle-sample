package com.sample.jetpack.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.jetpack.repository.database.dao.PostDao
import com.sample.jetpack.repository.database.entity.Post

@Database(
    entities = [Post::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private const val DATABASE_NAME = "post-database.db"
        internal const val DATABASE_VERSION = 1

        private var mInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
            }
            return mInstance!!
        }

    }
}