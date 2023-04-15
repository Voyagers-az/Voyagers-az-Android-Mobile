package com.natiqhaciyef.voyagers.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.screens.home.ContactScreen
import com.natiqhaciyef.voyagers.view.screens.home.MainScreenLine
import com.natiqhaciyef.voyagers.view.screens.home.PersonalInformationScreen
import com.natiqhaciyef.voyagers.view.screens.home.card.PaymentDetailsScreen
import com.natiqhaciyef.voyagers.view.screens.home.card.PaymentScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.FlightTicketScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.RentCarScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.HouseRentScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.tours.TourDetailsScreen
import com.natiqhaciyef.voyagers.view.screens.registration.LoginScreen
import com.natiqhaciyef.voyagers.view.screens.registration.RegisterScreen
import com.natiqhaciyef.voyagers.view.screens.registration.ResetPasswordScreen
import com.natiqhaciyef.voyagers.view.screens.registration.SplashScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenID.SplashScreen.name
    ) {

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
            TourDetailsScreen(
                data = DefaultModelImplementations.data,
                navController = navController
            )
        }

        composable(route = ScreenID.FlightTickets.name) {
            FlightTicketScreen()
        }

        composable(route = ScreenID.HouseRent.name) {
            HouseRentScreen()
        }

        composable(route = ScreenID.RentCar.name) {
            RentCarScreen()
        }

        composable(route = ScreenID.ContactDetails.name) {
            ContactScreen()
        }

        composable(route = ScreenID.Payment.name) {
            PaymentScreen(navController)
        }

        composable(
            route = "${ScreenID.PaymentDetails.name}/{paymentMethod}",
            arguments = listOf(
                navArgument(name = "paymentMethod") {
                    type = NavType.StringType
                }
            )
        ) {
            val paymentType = remember { it.arguments?.getString("paymentMethod") }

            PaymentDetailsScreen(
                paymentMethod = paymentType ?: "",
                navController = navController
            )
        }

        composable(route = ScreenID.PersonalInformation.name) {
            PersonalInformationScreen()
        }
    }
}