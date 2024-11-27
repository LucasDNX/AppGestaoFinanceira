package com.seuprojeto.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.seuprojeto.ui.navigation.AppNavigationBar
import com.seuprojeto.ui.navigation.NavigationRoutes
import com.seuprojeto.ui.screens.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { AppNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationRoutes.Home.route) { HomeScreen() }
            composable(NavigationRoutes.History.route) { HistoryScreen() }
            composable(NavigationRoutes.Currency.route) { CurrencyScreen() }
        }
    }
}
