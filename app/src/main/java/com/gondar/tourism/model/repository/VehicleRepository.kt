package com.gondar.tourism.model.repository

import com.gondar.tourism.model.entity.FuelLog
import com.gondar.tourism.model.entity.Vehicle

interface VehicleRepository {
    suspend fun getVehicleLocations(): List<Vehicle>
    suspend fun addFuelLog(log: FuelLog)
    suspend fun getFuelLogs(): List<FuelLog>
}
