package com.gondar.tourism.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gondar.tourism.R
import com.gondar.tourism.model.entity.Vehicle
import com.gondar.tourism.viewModel.TrackingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDashboardScreen(navController: NavController, viewModel: TrackingViewModel = hiltViewModel()) {
    var permissionGranted by remember { mutableStateOf(false) }

    RequestLocationPermission {
        permissionGranted = true
    }

    if (!permissionGranted) return

    val locations by viewModel.vehicleLocations.collectAsState()
    var currentVehicleIndex by remember { mutableStateOf(0) }
    val cameraPositionState = rememberCameraPositionState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Driver Dashboard") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (locations.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LaunchedEffect(locations) {
                    if (locations.isNotEmpty()) {
                        val target = LatLng(locations[0].lat, locations[0].lng)
                        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(target, 15f))
                    }
                }

                val selectedVehicle = remember { mutableStateOf<Vehicle?>(null) }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    val context = LocalContext.current
                    val vehicleIcon = remember {
                        val original = BitmapFactory.decodeResource(context.resources, R.drawable.vehicle)
                        val scaled = Bitmap.createScaledBitmap(original, 140, 70, false)
                        BitmapDescriptorFactory.fromBitmap(scaled)
                    }
                    locations.forEach { vehicle ->
                        Marker(
                            state = MarkerState(position = LatLng(vehicle.lat, vehicle.lng)),
                            title = vehicle.name,
                            snippet = "Tracking vehicle",
                            icon = vehicleIcon,
                            onClick = {
                                selectedVehicle.value = vehicle
                                false
                            }
                        )
                    }
                }

                selectedVehicle.value?.let { vehicle ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text("Vehicle: ${vehicle.vehicleId}", style = MaterialTheme.typography.titleMedium)
                            Text("Driver: ${vehicle.name}")
                            Text("Liters: ${vehicle.liters}")
                            Text("Time: ${Date(vehicle.timestamp)}")
                            Text("Location: ${vehicle.lat}, ${vehicle.lng}")
                            Button(onClick = { selectedVehicle.value = null }) {
                                Text("Close")
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("fuel") },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Person, contentDescription = "Fuel Logs")
                }

                FloatingActionButton(
                    onClick = {
                        if (locations.isNotEmpty()) {
                            currentVehicleIndex = (currentVehicleIndex + 1) % locations.size
                            val nextVehicle = locations[currentVehicleIndex]
                            val target = LatLng(nextVehicle.lat, nextVehicle.lng)
                            cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(target, 15f))
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Next Vehicle")
                }
            }
        }
    }
}