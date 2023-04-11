package com.natiqhaciyef.voyagers.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.screens.home.MainScreenLine
import com.natiqhaciyef.voyagers.view.screens.home.home_categories.FlightTicketScreen
import com.natiqhaciyef.voyagers.view.screens.home.home_categories.RentCarScreen
import com.natiqhaciyef.voyagers.view.screens.home.home_categories.HouseRentScreen
import com.natiqhaciyef.voyagers.view.screens.home.tours.TourDetailsScreen
import com.natiqhaciyef.voyagers.view.screens.registration.LoginScreen
import com.natiqhaciyef.voyagers.view.screens.registration.RegisterScreen
import com.natiqhaciyef.voyagers.view.screens.registration.ResetPasswordScreen
import com.natiqhaciyef.voyagers.view.screens.registration.SplashScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenID.SplashScreen.name) {
        composable(route = ScreenID.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = ScreenID.Register.name) {
            RegisterScreen(navController = navController)
        }

        composable(route = ScreenID.Login.name) {
            LoginScreen(navController = navController)
        }

        composable(route = ScreenID.ResetPassword.name) {
            ResetPasswordScreen(navController)
        }

        composable(route = ScreenID.MainScreenLine.name) {
            MainScreenLine(navController)
        }

        composable(route = ScreenID.TourDetails.name) {
            TourDetailsScreen(data = DefaultModelImplementations.data, navController = navController)
        }

        composable(route = ScreenID.FlightTickets.name){
            FlightTicketScreen()
        }

        composable(route = ScreenID.HouseRent.name){
            HouseRentScreen()
        }

        composable(route = ScreenID.RentCar.name){
            RentCarScreen()
        }

        composable(route = ScreenID.ContactDetails.name){}
    }
}