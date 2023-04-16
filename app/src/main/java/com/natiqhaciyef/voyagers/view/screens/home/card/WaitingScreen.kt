package com.natiqhaciyef.voyagers.view.screens.home.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.util.FontList
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple


@Preview
@Composable
fun WaitingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.second_success),
        )
        LottieAnimation(
            modifier = Modifier
                .fillMaxSize(),
            composition = composition,
            iterations = 1
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WaitingAnimationSuccess()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Sorğunuz uğurla göndərildi. \nTəsdiqləndikdən sonra sizinlə \nəlaqə saxlanacaq.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun WaitingAnimationSuccess() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.success),
    )
    LottieAnimation(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        composition = composition,
        iterations = 1
    )
}

