package com.natiqhaciyef.voyagers.view.screens.home.card

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import com.natiqhaciyef.voyagers.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.util.functions.nameSurnameSplitter
import com.natiqhaciyef.voyagers.view.navigation.NavigationData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.payment.PaymentViewModel

//@Preview
@Composable
fun PersonalInformationScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val permissionGrantBoolean = remember { mutableStateOf(true) }

    val isPermissionGranted = remember { mutableStateOf(false) }
    val imageData = remember { mutableStateOf<Uri?>(null) }

    val email = remember { mutableStateOf("") }
    val nameSurname = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val finCode = remember { mutableStateOf("") }

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val intentFromResult = result.data
            if (intentFromResult != null) {
                imageData.value = intentFromResult.data
            }
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intent)
            permissionGrantBoolean.value = true
        } else {
            // Permission Denied: Do something
            permissionGrantBoolean.value = false
        }
    }
    val context = LocalContext.current


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {}
    ) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(AppWhiteLightPurple),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 40.dp))
            PersonalInformationTopView(
                context = context,
                permissionLauncher = permissionLauncher,
                activityResultLauncher = activityResultLauncher,
                isPermissionGranted = isPermissionGranted,
                imageData = imageData
            )
            PersonalInformationMainPart(
                email = email,
                nameSurname = nameSurname,
                finCode = finCode,
                phone = phone
            )
            PersonalInformationBottomView(
                nameSurname = nameSurname,
                email = email,
                phone = phone,
                finCode = finCode,
                imageData = imageData,
                navController = navController
            )
        }
    }
}


@Composable
fun PersonalInformationTopView(
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>,
    imageData: MutableState<Uri?>
) {
    Image(
        painter = if (imageData.value != null) rememberImagePainter(data = imageData.value)
        else painterResource(id = R.drawable.id_card_icon),
        contentDescription = "Image",
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .clickable {
                imageSelector(
                    context = context,
                    permissionLauncher = permissionLauncher,
                    activityResultLauncher = activityResultLauncher,
                    isPermissionGranted = isPermissionGranted
                )
            },
        contentScale = if (imageData.value != null) ContentScale.Crop
        else ContentScale.Fit
    )
}


@Composable
fun PersonalInformationMainPart(
    email: MutableState<String>,
    finCode: MutableState<String>,
    phone: MutableState<String>,
    nameSurname: MutableState<String>,
) {
    Card(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
            .height(440.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                text = "Ad və Soyad",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                value = nameSurname.value,
                onValueChange = { nameSurname.value = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                singleLine = true,
                enabled = true,
                readOnly = false,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = AppGray
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                text = "E-poçt (qeydiyyatdan keçdiyiniz)",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                value = email.value,
                onValueChange = { email.value = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                ),
                singleLine = true,
                enabled = true,
                readOnly = false,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = AppGray
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                text = "Əlaqə nömrəsi",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                value = phone.value,
                onValueChange = { phone.value = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                ),
                singleLine = true,
                enabled = true,
                readOnly = false,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = AppGray
                )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                text = "Fərdi identifikasiya nömrəsi (FIN kod)",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 20.dp),
                value = finCode.value,
                onValueChange = { finCode.value = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                singleLine = true,
                enabled = true,
                readOnly = false,
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = "Description"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = AppDarkBlue,
                    unfocusedBorderColor = AppGray
                )
            )
        }
    }
}

@Composable
fun PersonalInformationBottomView(
    nameSurname: MutableState<String>,
    email: MutableState<String>,
    phone: MutableState<String>,
    finCode: MutableState<String>,
    imageData: MutableState<Uri?>,
    navController: NavController,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    Button(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .width(200.dp)
            .height(55.dp),
        onClick = {
            // save in firebase with viewModel 
            if (nameSurname.value.isNotEmpty()
                && email.value.isNotEmpty()
                && phone.value.isNotEmpty()
                && finCode.value.isNotEmpty()
                && imageData.value != null
            ) {
                NavigationData.userModel = UserModel(
                    id = 0,
                    name = nameSurname.value.nameSurnameSplitter()["name"]?: nameSurname.value,
                    surname = nameSurname.value.nameSurnameSplitter()["surname"]?: nameSurname.value,
                    dateOfBirth = "",
                    email = email.value,
                    phone = phone.value,
                    idNumber = finCode.value,
                    visaImage = "",
                    idImage = "${imageData.value}",
                    password = ""
                )
                navController.navigate(ScreenID.Payment.name)
            }
        },
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppDarkBlue
        )
    ) {
        Text(
            modifier = Modifier,
            text = "Təsdiqlə",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}


fun imageSelector(
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        isPermissionGranted.value = true
    } else {
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    if (isPermissionGranted.value) {
        val intentToGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }
}

