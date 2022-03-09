package com.practice.initialarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.initialarchitecture.data.local.model.AuthTokenEntity

@Database(entities = [AuthTokenEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }

}