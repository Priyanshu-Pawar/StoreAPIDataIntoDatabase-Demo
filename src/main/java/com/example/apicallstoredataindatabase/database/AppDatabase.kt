package com.example.apicallstoredataindatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apicallstoredataindatabase.ApiData

@Database(entities = [ApiData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apiDataDao(): ApiDataDao
}