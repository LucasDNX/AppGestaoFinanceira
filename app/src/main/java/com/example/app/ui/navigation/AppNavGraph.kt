package com.seuprojeto.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seuprojeto.ui.screens.*

@Composable
fun AppNavigationBar(navController: NavHostController) {
    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentDestination == NavigationRoutes.Home.route,
            onClick = { navController.navigate(NavigationRoutes.Home.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cotações") },
            label = { Text("Cotações") },
            selected = currentDestination == NavigationRoutes.Currency.route,
            onClick = { navController.navigate(NavigationRoutes.Currency.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Histórico") },
            label = { Text("Histórico") },
            selected = currentDestination == NavigationRoutes.History.route,
            onClick = { navController.navigate(NavigationRoutes.History.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }}
        )
    }
}
