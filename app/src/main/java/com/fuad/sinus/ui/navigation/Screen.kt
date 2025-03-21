package com.fuad.sinus.ui.navigation

sealed class Screen(val route: String) {
    object home: Screen("home")
    object diagnose: Screen("diagnose")
    object weather: Screen("weather")
    object welcomeDiagnose: Screen("welcome_diagnose")
}