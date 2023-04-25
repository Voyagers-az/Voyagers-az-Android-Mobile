package com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens.card_info_change

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.tour.AppealStatus
import com.natiqhaciyef.voyagers.data.model.tour.TourAppealModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.obj.PaymentMethodList
import com.natiqhaciyef.voyagers.view.navigation.CardNavData
import com.natiqhaciyef.voyagers.view.navigation.NavigationData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.payment.PaymentViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.settings.SettingsViewModel


@Composable
fun NewCardInfoScreen(
    id: Int,
    paymentType: String
){
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
            NewCardInfoTopView(id = id, paymentType = paymentType)
        }
    }
}

@Composable
private fun NewCardInfoTopView(
    paymentViewModel: PaymentViewModel = hiltViewModel(),
    id: Int,
    paymentType: String
) {
    val numberOnCard = remember { mutableStateOf("") }
    val nameOnCard = remember { mutableStateOf("") }
    val expirationDate = remember { mutableStateOf("") }
    val cvvCode = remember { mutableStateOf("") }

    val payments = remember { paymentViewModel.paymentModels }
    val paymentFilter = payments.value.filter {
        it.nameOnCard == nameOnCard.value &&
                it.numberOnCard == numberOnCard.value
    }
    val enabledData = paymentFilter.isNotEmpty()

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
            text = stringResource(id = R.string.insert_data),
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
                    text = stringResource(id = R.string.card_name_surname),
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
                    text = stringResource(id = R.string.card_number),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            },
            trailingIcon = {},
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
                        text = stringResource(id = R.string.card_date),
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
                        text = stringResource(id = R.string.cvv),
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
                    val paymentDataModel = PaymentDataModel(
                        id = id,
                        paymentType = paymentType,
                        nameOnCard = nameOnCard.value,
                        numberOnCard = numberOnCard.value,
                        expirationDate = expirationDate.value,
                        cvvCode = cvvCode.value.toInt(),
                        userModel = CardNavData.userModel ?: DefaultModelImplementations.userModelEmpty
                    )
                    paymentViewModel.updateCardInfo(
                        id = id,
                        payment = paymentDataModel
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue,
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

fun cardTypeToImageFinder(paymentMethod: String): Int {
    return when (paymentMethod.lowercase()) {
        PaymentTypes.VISA.mainName.lowercase() -> PaymentMethodList.list[0].image
        PaymentTypes.MASTERCARD.mainName.lowercase() -> PaymentMethodList.list[1].image
        PaymentTypes.PAYPAL.mainName.lowercase() -> PaymentMethodList.list[2].image
        else -> 0
    }
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

