package com.natiqhaciyef.voyagers.view.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.util.obj.FontList
import com.natiqhaciyef.voyagers.view.components.BottomShadow
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppBrown
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.Red
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LoginTopView()
        LoginMainPart(email, password, navController) {
            viewModel.loginUser(email.value, password.value) {
                navController.navigate(ScreenID.MainScreenLine.name)
            }
        }
    }
}

@Composable
private fun LoginTopView() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.login_animation),
    )
    Spacer(modifier = Modifier.height(45.dp))
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Text(
        text = "Daxil ol",
        fontSize = 25.sp,
        color = Color.Black,
        fontFamily = FontList.fontFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(30.dp))
}


@Composable
private fun LoginMainPart(
    email: MutableState<String> = mutableStateOf(""),
    password: MutableState<String> = mutableStateOf(""),
    navController: NavController,
    content: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        backgroundColor = AppDarkBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email).copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "E-poçt",
                        color = AppGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = AppGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue,
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            BottomShadow(padding = 23.dp)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Şifrə",
                        color = AppGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = AppGray
                    )
                },
                visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(
                        modifier = Modifier.padding(end = 15.dp),
                        onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            )

            BottomShadow(padding = 23.dp)

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Şifrəni unutmusuz ?",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .clickable {
                        navController.navigate(ScreenID.ResetPassword.name)
                    },
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(45.dp))

            val context = LocalContext.current

            Button(
                modifier = Modifier
                    .height(55.dp)
                    .width(200.dp),
                onClick = {
                    content()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppBrown
                )
            ) {
                Text(
                    text = "Daxil ol",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            BottomShadow(
                modifier = Modifier
                    .width(165.dp)
                    .height(8.dp)
                    .padding(horizontal = 0.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Transparent,
                            )
                        )
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Yeni hesab yaratmaq ?",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ScreenID.Register.name)
                        },
                    text = "Qeydiyyatdan keç",
                    color = Red,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}


