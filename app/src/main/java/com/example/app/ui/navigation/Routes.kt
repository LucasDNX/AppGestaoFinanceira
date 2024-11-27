package com.seuprojeto.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes("home")
    object History : NavigationRoutes("history")
    object Currency : NavigationRoutes("currency")
}
