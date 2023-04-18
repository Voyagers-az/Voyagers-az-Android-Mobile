package com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.settings.SettingsViewModel

@Preview
@Composable
fun UsernameChangeScreen(
    viewModel: SettingsViewModel = hiltViewModel()
){
    val user = remember { viewModel.fumList }


}