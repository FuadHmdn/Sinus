package com.fuad.sinus.ui.navigation

sealed class Screen(val route: String) {
    object home: Screen("home")
    object diagnose: Screen("diagnose")
    object diagnoseResult : Screen("diagnose_result/{d1}/{d2}/{d3}/{d4}") {
        fun createRoute(d1: Double, d2: Double, d3: Double, d4: Double) = "diagnose_result/$d1/$d2/$d3/$d4"
    }
    object artikel : Screen("artikel/{a}/{b}/{c}") {
        fun createRoute(a: Int, b: Int, c: Int) = "artikel/$a/$b/$c"
    }
    object welcomeDiagnose: Screen("welcome_diagnose")
}