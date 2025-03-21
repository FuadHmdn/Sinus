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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fuad.sinus.ui.diagnose.Diagnose
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
                Diagnose(){

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
        }
    }
}