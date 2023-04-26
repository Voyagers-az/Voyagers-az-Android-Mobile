package com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.flight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TicketRequestScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}


@Composable
private fun TicketRequestScreenTopView() {

}


@Composable
private fun TicketRequestMainPart() {

}