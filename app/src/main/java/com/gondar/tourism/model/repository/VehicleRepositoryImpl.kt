package com.gondar.tourism.model.repository

import com.gondar.tourism.model.api.VehicleApi
import com.gondar.tourism.model.dao.FuelLogDao
import com.gondar.tourism.model.entity.FuelLog
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val api: VehicleApi,
    private val dao: FuelLogDao
): VehicleRepository {
    override suspend fun getVehicleLocations() = api.getVehicles()
    override suspend fun addFuelLog(log: FuelLog) = dao.insert(log)
    override suspend fun getFuelLogs() = dao.getAll()
}
