package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple

@Preview
@Composable
fun RentCarScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(AppAquatic)
        )

        Column(modifier = Modifier.fillMaxSize()) {

        }
    }
}


@Preview
@Composable
private fun RentCarTopView() {
//    OutlinedTextField(value = ,
//        onValueChange = )
}