package com.gondar.tourism.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gondar.tourism.model.entity.FuelLog
import com.gondar.tourism.model.entity.Vehicle
import com.gondar.tourism.model.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {
    val vehicleLocations = MutableStateFlow<List<Vehicle>>(emptyList())
    val fuelLogs = MutableStateFlow<List<FuelLog>>(emptyList())

    init {
        fetchVehicleLocations()
    }

    fun fetchVehicleLocations() {
        viewModelScope.launch {
            vehicleLocations.value = repository.getVehicleLocations()
        }
    }

    fun addFuelLog(log: FuelLog) {
        viewModelScope.launch {
            repository.addFuelLog(log)
            fuelLogs.value = repository.getFuelLogs()
        }
    }
}