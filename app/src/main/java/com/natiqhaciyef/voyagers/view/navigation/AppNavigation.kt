package com.natiqhaciyef.voyagers.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.voyagers.view.screens.*
import com.natiqhaciyef.voyagers.view.screens.home.MainScreenLine
import com.natiqhaciyef.voyagers.view.screens.registration.LoginScreen
import com.natiqhaciyef.voyagers.view.screens.registration.RegisterScreen
import com.natiqhaciyef.voyagers.view.screens.registration.SplashScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenID.SplashScreen.name) {
        composable(route = ScreenID.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = ScreenID.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }

        composable(route = ScreenID.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(route = ScreenID.ResetPasswordScreen.name) {
            ResetPasswordScreen(navController)
        }

        composable(route = ScreenID.MainScreenLine.name) {
            MainScreenLine()
        }
    }
}