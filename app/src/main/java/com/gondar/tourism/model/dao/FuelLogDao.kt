package com.gondar.tourism.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gondar.tourism.model.entity.FuelLog

@Dao
interface FuelLogDao {
    @Insert
    suspend fun insert(log: FuelLog)

    @Query("SELECT * FROM FuelLog")
    suspend fun getAll(): List<FuelLog>
}