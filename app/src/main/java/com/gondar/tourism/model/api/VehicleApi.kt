package com.gondar.tourism.model.api

import com.gondar.tourism.model.entity.Vehicle
import retrofit2.http.GET

interface VehicleApi {
    @GET("gondartour")
    suspend fun getVehicles(): List<Vehicle>
}