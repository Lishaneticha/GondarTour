package com.gondar.tourism.model.repository

import com.gondar.tourism.model.api.VehicleApi
import com.gondar.tourism.model.dao.FuelLogDao
import com.gondar.tourism.model.entity.FuelLog
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val api: VehicleApi,
    private val dao: FuelLogDao
) {
    suspend fun getVehicleLocations() = api.getVehicles()
    suspend fun addFuelLog(log: FuelLog) = dao.insert(log)
    suspend fun getFuelLogs() = dao.getAll()
}
