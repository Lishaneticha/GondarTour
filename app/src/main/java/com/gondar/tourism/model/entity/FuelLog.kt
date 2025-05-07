package com.gondar.tourism.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuelLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vehicleId: String,
    val liters: Double,
    val timestamp: Long
)