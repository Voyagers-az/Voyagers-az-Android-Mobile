package com.natiqhaciyef.voyagers.view.screens.home
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.components.NavBar
import com.natiqhaciyef.voyagers.view.screens.home.main.HomeScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.UserProfileScreen
import com.natiqhaciyef.voyagers.view.screens.home.main.tours.TourScreen


//@Preview
@Composable
fun MainScreenLine(
    navController: NavController,
    selectedIndex: MutableState<Int> = DefaultModelImplementations.selectedIndex
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavBar(selectedIndex = selectedIndex)
        }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen(navController = navController)
            }
            1 -> {
                TourScreen(navController = navController)
            }
            2 -> {
                UserProfileScreen(navController = navController)
            }
        }
    }
}