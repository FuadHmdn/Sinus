package com.fuad.sinus.ui.navigation

sealed class Screen(val route: String) {
    object home: Screen("home")
    object diagnose: Screen("diagnose")
    object diagnoseResult : Screen("diagnose_result/{d1}/{d2}/{d3}/{d4}") {
        fun createRoute(d1: Double, d2: Double, d3: Double, d4: Double) = "diagnose_result/$d1/$d2/$d3/$d4"
    }
    object welcomeDiagnose: Screen("welcome_diagnose")
}