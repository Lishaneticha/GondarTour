package com.gondar.tourism.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GondarTourNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "driver") {
        composable("driver") { DriverDashboardScreen(navController) }
        composable("fuel") { FuelLogScreen(navController) }
    }
}