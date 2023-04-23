package com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens

import android.nfc.cardemulation.CardEmulation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.screens.home.main.ContactTopView
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.payment.PaymentViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.settings.SettingsViewModel


@Composable
fun CardInfoChangeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = AppWhiteLightPurple
    ) {
        it.calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(AppDarkBlue)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 60.dp),
                text = stringResource(id = R.string.card_info_change_title),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(50.dp))
            CardInfoChangeTopView()
        }
    }
}


@Composable
fun CardInfoChangeTopView(
    viewModel: SettingsViewModel = hiltViewModel(),
    paymentViewModel: PaymentViewModel = hiltViewModel()
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val cardNumber = remember { mutableStateOf("") }
    val cardUserName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val fums = remember { viewModel.fumList }
    val fumFilter = fums.value.filter { it.password == password.value }
    val fumEnabled = fumFilter.isNotEmpty()

    val payments = remember { paymentViewModel.paymentModels }
    val paymentFilter = payments.value.filter {
        it.nameOnCard == cardUserName.value &&
                it.numberOnCard == cardNumber.value
    }
    val enabledData = paymentFilter.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.card_number),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = cardNumber.value,
            onValueChange = {
                cardNumber.value = it
            },
            readOnly = false,
            singleLine = true,
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.card_numner_enter),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppGray
                )
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            trailingIcon = {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Card name"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                textColor = Color.Black,
                trailingIconColor = Color.Black,
                cursorColor = AppDarkBlue
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.card_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = cardUserName.value,
            onValueChange = {
                cardUserName.value = it
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            readOnly = false,
            singleLine = true,
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.card_name_enter),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppGray
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.NewReleases,
                    contentDescription = "Name on card"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                textColor = Color.Black,
                cursorColor = AppDarkBlue,
                trailingIconColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 23.dp),
            text = stringResource(id = R.string.password_info),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            singleLine = true,
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.password_info_enter),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppGray
                )
            },
            visualTransformation =
            if (passwordVisible)
                VisualTransformation.None
            else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                trailingIconColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Black,
                cursorColor = AppDarkBlue
            ),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )


        Spacer(modifier = Modifier.height(100.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = fumEnabled && enabledData,
            onClick = {
                      // change data
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue,
                disabledBackgroundColor = AppGray
            )
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.change),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}