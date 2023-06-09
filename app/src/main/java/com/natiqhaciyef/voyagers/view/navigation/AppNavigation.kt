package com.natiqhaciyef.voyagers.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.screens.home.main.ContactScreen
import com.natiqhaciyef.voyagers.view.screens.home.MainScreenLine
import com.natiqhaciyef.voyagers.view.screens.home.card.PersonalInformationScreen
import com.natiqhaciyef.voyagers.view.screens.home.card.PaymentScreen
import com.natiqhaciyef.voyagers.view.screens.home.card.WaitingScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.car.CarDetailsScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.flight.FlightTicketScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.car.RentCarScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.flight.TicketRequestScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.hotel.HouseRentScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.card_info_change.CardInfoChangeScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.SavedToursScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.TourAppeal
import com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.UsernameChangeScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.card_info_change.NewCardInfoScreen
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
            FlightTicketScreen(navController = navController)
        }

        composable(route = ScreenID.TicketRequest.name) {
            TicketRequestScreen()
        }

        composable(route = ScreenID.HouseRent.name) {
            HouseRentScreen()
        }

        composable(route = ScreenID.RentCar.name) {
            RentCarScreen()
        }

        composable(route = ScreenID.CarDetails.name) {
            CarDetailsScreen()
        }

        composable(route = ScreenID.ContactDetails.name) {
            ContactScreen(navController)
        }

        composable(route = ScreenID.Payment.name) {
            PaymentScreen(navController)
        }


        composable(route = ScreenID.PersonalInformation.name) {
            PersonalInformationScreen(navController)
        }

        composable(route = ScreenID.SavedTours.name) {
            SavedToursScreen()
        }

        composable(route = ScreenID.TourAppeal.name) {
            TourAppeal()
        }

        composable(route = ScreenID.ResetUserName.name) {
            UsernameChangeScreen()
        }

        composable(route = ScreenID.CardInfoChange.name) {
            CardInfoChangeScreen(navController)
        }

        composable(
            route = "${ScreenID.NewCardInfoChange.name}/{id}/{paymentType}",
            arguments = arrayListOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("paymentType") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = remember { it.arguments?.getInt("id") }
            val paymentType = remember { it.arguments?.getString("paymentType") }
            NewCardInfoScreen(
                id ?: 0,
                paymentType ?: "Visa"
            )
        }

        composable(route = ScreenID.Waiting.name) {
            WaitingScreen()
        }

    }
}