package com.natiqhaciyef.voyagers.view.screens

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel
import kotlinx.coroutines.*

//@Preview
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        coroutineScope.launch(Dispatchers.Main) {
            delay(1500)
            if (viewModel.allUsersState.value.isNotEmpty())
                navController.navigate(ScreenID.MainScreenLine.name)
            else
                navController.navigate(ScreenID.LoginScreen.name)
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.voyagers_icon),
            contentDescription = "Application icon"
        )
    }
}