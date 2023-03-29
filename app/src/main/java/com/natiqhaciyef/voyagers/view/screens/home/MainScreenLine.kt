package com.natiqhaciyef.voyagers.view.screens.home
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.natiqhaciyef.voyagers.view.components.NavBar
import com.natiqhaciyef.voyagers.view.screens.UserProfileScreen


@Preview
@Composable
fun MainScreenLine() {
    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavBar(selectedIndex = selectedIndex)
        }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen()
            }
            1 -> {
                TourScreen()
            }
            2 -> {
                UserProfileScreen()
            }
        }
    }
}