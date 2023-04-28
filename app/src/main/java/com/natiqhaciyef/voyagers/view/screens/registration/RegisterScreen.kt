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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
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
import com.airbnb.lottie.compose.rememberLottieComposition
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.util.obj.FontList
import com.natiqhaciyef.voyagers.view.components.BottomShadow
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppBrown
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.Red
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel

//@Preview
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val user by remember { viewModel.userState }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        RegisterTopView()
        RegisterMainPart(username, email, phone, password, navController) {
            if (user.email != email.value && phone.value.isNotEmpty() &&
                username.value.isNotEmpty() && password.value.isNotEmpty()
            ) {
                if (
                    email.value.endsWith("@gmail.com") &&
                    ((phone.value.startsWith("055")) || (phone.value.startsWith("050")) ||
                            (phone.value.startsWith("099")) || (phone.value.startsWith("070")) ||
                            (phone.value.startsWith("077"))) &&
                    password.value.length >= 8 && phone.value.length == 10
                ) {
                    viewModel.registerUser(
                        email.value,
                        password.value,
                        username.value,
                        phone.value
                    ) {
                        navController.navigate(ScreenID.Login.name)
                    }
                }
            }
        }
    }
}

@Composable
private fun RegisterTopView() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.register_animation)
    )
    Spacer(modifier = Modifier.height(15.dp))
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        composition = composition,
    )

    Text(
        text = stringResource(id = R.string.register),
        fontSize = 25.sp,
        color = Color.Black,
        fontFamily = FontList.fontFamily,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
private fun RegisterMainPart(
    username: MutableState<String> = mutableStateOf(""),
    email: MutableState<String> = mutableStateOf(""),
    phone: MutableState<String> = mutableStateOf(""),
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
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.username),
                        color = AppGray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text).copy(
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Username",
                        tint = AppGray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = colorResource(id = MaterialTheme.colors.textInputColor),
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue
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
                        text = stringResource(id = R.string.email),
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
                    textColor = colorResource(id = MaterialTheme.colors.textInputColor),
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = AppDarkBlue
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
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone).copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.phone),
                        color = AppGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",
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
                    cursorColor = AppDarkBlue
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
                        text = stringResource(id = R.string.password),
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

            Spacer(modifier = Modifier.height(25.dp))

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
                    text = stringResource(id = R.string.register),
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
                    text = stringResource(id = R.string.have_an_account),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ScreenID.Login.name)
                        },
                    text = stringResource(id = R.string.enter),
                    color = Red,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}


// Adding extra colors for Dark And Light Theme
@get: Composable
val Colors.textInputColor: Int
    get() = if (isLight) R.color.black else R.color.black

//Image