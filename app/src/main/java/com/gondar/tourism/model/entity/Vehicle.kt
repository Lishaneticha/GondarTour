package com.gondar.tourism.model.entity

data class Vehicle(
    val id: String,
    val name: String,
    val lat: Double,
    val lng: Double,
    val vehicleId: String,
    val liters: Double,
    val timestamp: Long
)