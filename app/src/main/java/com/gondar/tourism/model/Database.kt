package com.gondar.tourism.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gondar.tourism.model.dao.FuelLogDao
import com.gondar.tourism.model.entity.FuelLog

@Database(entities = [FuelLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fuelLogDao(): FuelLogDao
}