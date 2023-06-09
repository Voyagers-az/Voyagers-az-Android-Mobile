package com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens

import android.util.Log
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.settings.SettingsViewModel

@Preview
@Composable
fun UsernameChangeScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val oldUsername = remember { mutableStateOf("") }
    val newUsername = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        it.calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppWhiteLightPurple)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(AppDarkBlue)
            )
            UserNameChangeMainPart(oldUsername, newUsername, email)
        }
    }
}


@Composable
fun UserNameChangeMainPart(
    oldUsername: MutableState<String>,
    newUsername: MutableState<String>,
    password: MutableState<String>,
    viewModel: SettingsViewModel = hiltViewModel(),
    registerViewModel: RegistrationViewModel = hiltViewModel()
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val fums = remember { viewModel.fumList }
    val fumFilter = fums.value.filter {
        it.username == oldUsername.value &&
                it.password == password.value

    }

    val fum = if (fumFilter.isNotEmpty()) fumFilter[0] else DefaultModelImplementations.firebaseUserModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.reset_username_title),
            fontSize = 23.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.previous_user_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = oldUsername.value,
            onValueChange = {
                oldUsername.value = it
            },
            readOnly = false,
            singleLine = true,
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.previous_user_name_enter),
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
                    contentDescription = "Previous account"
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
            text = stringResource(id = R.string.new_user_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = newUsername.value,
            onValueChange = {
                newUsername.value = it
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
                    text = stringResource(id = R.string.new_user_name_enter),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppGray
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.NewReleases,
                    contentDescription = "New account"
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
            enabled = fum != DefaultModelImplementations.firebaseUserModel,
            onClick = {
                viewModel.deleteFumFromFirestore(
                    fum
                )
                viewModel.sendUserDataToFirebase(
                    FirebaseUserModel(
                        username = newUsername.value,
                        email = fum.email,
                        phone = fum.phone,
                        password = fum.password
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue,
                disabledBackgroundColor = AppGray
            )
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.submit),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}