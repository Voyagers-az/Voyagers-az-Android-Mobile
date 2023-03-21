package com.natiqhaciyef.voyagers.view.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.natiqhaciyef.voyagers.R

@Preview
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.background(color = Color.White),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.voyagers_icon),
            contentDescription = "Application icon"
        )
    }
}