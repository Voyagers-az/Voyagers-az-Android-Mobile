package com.natiqhaciyef.voyagers.view

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.natiqhaciyef.voyagers.view.ui.theme.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.natiqhaciyef.voyagers.view.navigation.AppNavigation
import com.natiqhaciyef.voyagers.view.ui.theme.VoyagersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    VoyagersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                content()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {

    }
}

@get: Composable
val Colors.myExtraColor: Color
    get() = if (isLight) Red else Green
