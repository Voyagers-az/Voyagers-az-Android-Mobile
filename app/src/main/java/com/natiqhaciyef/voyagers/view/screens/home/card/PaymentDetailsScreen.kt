package com.natiqhaciyef.voyagers.view.screens.home.card

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes
import com.natiqhaciyef.voyagers.util.PaymentMethodList
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple

//@Preview
@Composable
fun PaymentDetailsScreen(
    paymentMethod: String = "Visa",
    navController: NavController
) {
    val nameOnCard = remember { mutableStateOf("") }
    val numberOnCard = remember { mutableStateOf("") }
    val expirationDate = remember { mutableStateOf("") }
    val cvvCode = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            if (nameOnCard.value.isNotEmpty()
                || numberOnCard.value.isNotEmpty()
                || expirationDate.value.isNotEmpty()
                || cvvCode.value.isNotEmpty()
            ) {
                FloatingActionButton(
                    onClick = {
                        nameOnCard.value = ""
                        numberOnCard.value = ""
                        expirationDate.value = ""
                        cvvCode.value = ""
                    },
                    shape = CircleShape,
                    backgroundColor = AppDarkBlue,
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh filters",
                        tint = AppWhiteLightPurple,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }
        }
    ) {
        it.calculateBottomPadding()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppWhiteLightPurple),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = "Məlumatları daxil edin",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
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
                        painter = painterResource(id = cardTypeToImageFinder(paymentMethod)),
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
                    )
                    navController.navigate(ScreenID.PersonalInformation.name)
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

