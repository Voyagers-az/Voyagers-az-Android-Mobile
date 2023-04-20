package com.natiqhaciyef.voyagers.view.screens.home.card

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.data.model.payment.PaymentChoiceModel
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes
import com.natiqhaciyef.voyagers.data.model.tour.AppealStatus
import com.natiqhaciyef.voyagers.data.model.tour.TourAppealModel
import com.natiqhaciyef.voyagers.util.obj.PaymentMethodList
import com.natiqhaciyef.voyagers.view.navigation.NavigationData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.payment.PaymentViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.settings.SettingsViewModel

//@Preview
@Composable
fun PaymentScreen(
    navController: NavController
) {
    val selectedPayment = remember { mutableStateOf("") }
    val visaSelected = remember { mutableStateOf(false) }
    val masterCardSelected = remember { mutableStateOf(false) }
    val paypalSelected = remember { mutableStateOf(false) }
    val nameOnCard = remember { mutableStateOf("") }
    val numberOnCard = remember { mutableStateOf("") }
    val expirationDate = remember { mutableStateOf("") }
    val cvvCode = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .height(280.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(AppDarkBlue),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        text = "Ödəniş metodunu seçin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[0],
                        isSelected = visaSelected
                    ) {
                        selectedPayment.value = "Visa"
                        visaSelected.value = true
                        masterCardSelected.value = false
                        paypalSelected.value = false
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[1],
                        isSelected = masterCardSelected
                    ) {
                        selectedPayment.value = "Master card"
                        visaSelected.value = false
                        masterCardSelected.value = true
                        paypalSelected.value = false
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentCardItems(
                        paymentMethod = PaymentMethodList.list[2],
                        isSelected = paypalSelected
                    ) {
                        selectedPayment.value = "PayPal"
                        visaSelected.value = false
                        masterCardSelected.value = false
                        paypalSelected.value = true
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            if (selectedPayment.value.isNotEmpty()) {
                PaymentDetailsMainPart(
                    nameOnCard = nameOnCard,
                    numberOnCard = numberOnCard,
                    expirationDate = expirationDate,
                    cvvCode = cvvCode,
                    paymentMethod = selectedPayment,
                    navController = navController
                )
            }
        }
    }
}


@Composable
private fun PaymentCardItems(
    paymentMethod: PaymentChoiceModel,
    isSelected: MutableState<Boolean>,
    content: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(AppWhiteLightPurple)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = paymentMethod.image),
                contentDescription = "Payment Method",
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                text = "${paymentMethod.type.mainName}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(20.dp))

            RadioButton(
                selected = isSelected.value,
                onClick = { content() },
            )
        }
    }
}


@Composable
fun PaymentDetailsMainPart(
    nameOnCard: MutableState<String>,
    numberOnCard: MutableState<String>,
    expirationDate: MutableState<String>,
    cvvCode: MutableState<String>,
    paymentMethod: MutableState<String>,
    viewModel: PaymentViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppWhiteLightPurple),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Məlumatları daxil edin",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = nameOnCard.value,
            onValueChange = {
                nameOnCard.value = it
            },
            enabled = true,
            singleLine = true,
            readOnly = false,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                textColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = AppGray,
                backgroundColor = Color.White
            ),
            label = {
                Text(
                    modifier = Modifier,
                    text = "Kart sahibinin ad və soyadı",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = numberOnCard.value,
            onValueChange = {
                if (numberOnCard.value.length <= 16)
                    numberOnCard.value = it
            },
            enabled = true,
            singleLine = true,
            readOnly = false,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                textColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = AppGray,
                backgroundColor = Color.White
            ),
            label = {
                Text(
                    modifier = Modifier,
                    text = "Kart nömrəsi",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            },
            trailingIcon = {
                Image(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(35.dp),
                    painter = painterResource(id = cardTypeToImageFinder(paymentMethod.value)),
                    contentDescription = "Card type"
                )
            },
            visualTransformation = { number ->
                formatOtherCardNumbers(numberOnCard.value)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 20.dp),
                value = expirationDate.value,
                onValueChange = {
                    if (expirationDate.value.length <= 4)
                        expirationDate.value = it
                },
                enabled = true,
                singleLine = true,
                readOnly = false,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = AppGray,
                    backgroundColor = Color.White
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Date"
                    )
                },
                label = {
                    Text(
                        modifier = Modifier,
                        text = "Tarix",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                },
                visualTransformation = { number ->
                    formatExpirationDate(expirationDate.value)
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 20.dp),
                value = cvvCode.value,
                onValueChange = {
                    if (cvvCode.value.length < 3)
                        cvvCode.value = it
                },
                enabled = true,
                singleLine = true,
                readOnly = false,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = AppDarkBlue,
                    textColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = AppGray,
                    backgroundColor = Color.White
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Help,
                        contentDescription = "Question"
                    )
                },
                label = {
                    Text(
                        modifier = Modifier,
                        text = "CVV kod",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(55.dp),
            shape = RoundedCornerShape(7.dp),
            onClick = {
                if (
                    numberOnCard.value.isNotEmpty()
                    && nameOnCard.value.isNotEmpty()
                    && cvvCode.value.isNotEmpty()
                    && expirationDate.value.isNotEmpty()
                ) {
                    if (NavigationData.userModel != null) {
                        val paymentDataModel = PaymentDataModel(
                            id = 0,
                            paymentType = paymentMethod.value,
                            nameOnCard = nameOnCard.value,
                            numberOnCard = numberOnCard.value,
                            expirationDate = expirationDate.value,
                            cvvCode = 0,
                            userModel = NavigationData.userModel!!
                        )
                        val paymentDataModelForLocalDb = PaymentDataModel(
                            id = 0,
                            paymentType = paymentMethod.value,
                            nameOnCard = nameOnCard.value,
                            numberOnCard = numberOnCard.value,
                            expirationDate = expirationDate.value,
                            cvvCode = cvvCode.value.toInt(),
                            userModel = NavigationData.userModel!!
                        )

//                        viewModel.sendPaymentInfoToFirebase(paymentDataModel)
                        settingsViewModel.sendTourAppealStatusToFirebase(
                            TourAppealModel(
                                tourModel = NavigationData.tourModel!!,
                                payment = paymentDataModel,
                                status = AppealStatus.NonStatus.mainStatus
                            )
                        )
                        navController.navigate(ScreenID.Waiting.name)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue,
            )
        ) {
            Text(
                modifier = Modifier,
                text = "Təsdiqlə",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun cardTypeToImageFinder(paymentMethod: String): Int {
    return when (paymentMethod.lowercase()) {
        PaymentTypes.VISA.mainName.lowercase() -> PaymentMethodList.list[0].image
        PaymentTypes.MASTERCARD.mainName.lowercase() -> PaymentMethodList.list[1].image
        PaymentTypes.PAYPAL.mainName.lowercase() -> PaymentMethodList.list[2].image
        else -> 0
    }
}


fun formatOtherCardNumbers(text: String): TransformedText {

    val trimmed = if (text.length >= 16) text.substring(0..15) else text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += " "
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

fun formatExpirationDate(text: String): TransformedText {

    val trimmed = if (text.length >= 5) text.substring(0..3) else text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 3 == 1) out += "/"
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset < 4) return offset
            if (offset == 4) return offset + 1
            if (offset > 4) return offset
            return 4
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < 4) return offset
            if (offset >= 4) return offset - 1
            return offset
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

