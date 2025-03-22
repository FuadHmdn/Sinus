package com.fuad.sinus

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fuad.sinus.ui.diagnose.Diagnose
import com.fuad.sinus.ui.diagnose.DiagnoseResult
import com.fuad.sinus.ui.diagnose.DiagnoseViewModel
import com.fuad.sinus.ui.diagnose.WelcomeDiagnose
import com.fuad.sinus.ui.home.Home
import com.fuad.sinus.ui.navigation.Screen

@Composable
fun SinusApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it })
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it })
            },
        ){
            composable(Screen.home.route){
                Home(LocalContext.current){
                    navController.navigate(Screen.welcomeDiagnose.route)
                }
            }
            composable(Screen.diagnose.route){
                Diagnose(){ d1, d2, d3, d4 ->
                    navController.navigate(Screen.diagnoseResult.createRoute(d1, d2, d3, d4))
                }
            }
            composable(Screen.welcomeDiagnose.route){
                WelcomeDiagnose(
                    modifier = Modifier
                        .padding(top = 34.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ){
                    navController.navigate(Screen.diagnose.route)
                }
            }
            composable(
                route = Screen.diagnoseResult.route,
                arguments = listOf(
                    navArgument("d1") { type = NavType.FloatType },
                    navArgument("d2") { type = NavType.FloatType },
                    navArgument("d2") { type = NavType.FloatType },
                    navArgument("d4") { type = NavType.FloatType }
                )
            ) { backStackEntry ->
                val d1 = backStackEntry.arguments?.getFloat("d1") ?: 0f
                val d2 = backStackEntry.arguments?.getFloat("d2") ?: 0f
                val d3 = backStackEntry.arguments?.getFloat("d3") ?: 0f
                val d4 = backStackEntry.arguments?.getFloat("d4") ?: 0f
                DiagnoseResult(Modifier.padding(start = 16.dp, end = 16.dp), d1, d2, d3, d4){
                    navController.navigate(Screen.home.route){
                        popUpTo(Screen.diagnose.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}