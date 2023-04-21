package com.natiqhaciyef.voyagers.view.screens.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple


//@Preview
@Composable
fun ContactScreen(
    navController: NavController
) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val isFilled = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.calculateTopPadding()

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppWhiteLightPurple)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ContactTopView()
                ContactMainPart(
                    username = username,
                    email = email,
                    phone = phone,
                    description = description
                )

                if (
                    username.value.isNotEmpty()
                    && email.value.isNotEmpty()
                    && phone.value.isNotEmpty()
                    && description.value.isNotEmpty()
                ) {
                    isFilled.value = true
                }
                Spacer(modifier = Modifier.height(20.dp))

                ContactBottomView(
                    navController = navController,
                    isFilled = isFilled
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun ContactTopView() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.contact_panel),
    )
    Spacer(modifier = Modifier.height(20.dp))

    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Spacer(modifier = Modifier.height(30.dp))
}


@Composable
fun ContactMainPart(
    username: MutableState<String>,
    email: MutableState<String>,
    phone: MutableState<String>,
    description: MutableState<String>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(490.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppDarkBlue)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                text = stringResource(id = R.string.contact_us),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                singleLine = true,
                readOnly = false,
                enabled = true,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(30.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Username"
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.username),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppGray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue,

                    ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                singleLine = true,
                readOnly = false,
                enabled = true,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(30.dp),
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.email),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppGray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue,
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                singleLine = true,
                readOnly = false,
                enabled = true,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(30.dp),
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone"
                    )
                },
                placeholder = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.phone),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppGray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue,
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 20.dp),
                value = description.value,
                onValueChange = {
                    description.value = it
                },
                singleLine = false,
                readOnly = false,
                enabled = true,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.topic),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppGray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue,
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}

@Composable
fun ContactBottomView(
    navController: NavController,
    isFilled: MutableState<Boolean>
) {
    Button(
        modifier = Modifier
            .width(200.dp)
            .height(55.dp),
        enabled = isFilled.value,
        onClick = {
            navController.navigate(ScreenID.Waiting.name)
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppDarkBlue,
            disabledBackgroundColor = AppGray
        )
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.send),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
