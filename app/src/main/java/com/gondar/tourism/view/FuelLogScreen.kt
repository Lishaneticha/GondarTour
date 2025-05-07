package com.gondar.tourism.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gondar.tourism.viewModel.TrackingViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuelLogScreen(navController: NavController, viewModel: TrackingViewModel = hiltViewModel()) {
    val logs by viewModel.vehicleLocations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fuel Logs") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(logs) { log ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Vehicle ID: ${log.vehicleId}", style = MaterialTheme.typography.titleMedium)
                        Text("Driver: ${log.name}", style = MaterialTheme.typography.bodyMedium)
                        Text("Liters: ${log.liters}", style = MaterialTheme.typography.bodyMedium)
                        Text("Time: ${Date(log.timestamp)}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}