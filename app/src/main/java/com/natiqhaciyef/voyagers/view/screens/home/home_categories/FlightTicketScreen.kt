package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun FlightTicketScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(AppAquatic),
        )
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            FlightTicketTopView()
            FlightTicketMainPart()
        }
    }
}


@Preview
@Composable
private fun FlightTicketTopView(
    from: MutableState<String> = mutableStateOf(""),
    to: MutableState<String> = mutableStateOf("")
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // from
        OutlinedTextField(
            modifier = Modifier
                .width(165.dp)
                .height(65.dp),
            value = from.value,
            onValueChange = {
                from.value = it
            },
            enabled = true,
            readOnly = false,
            textStyle = TextStyle.Default.copy(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = {
                Text(
                    text = "Location",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedLabelColor = AppGray,
                focusedLabelColor = Color.Black,
                cursorColor = AppDarkBlue,
                trailingIconColor = AppDarkBlue
            ),
            shape = RoundedCornerShape(7.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .width(165.dp)
                .height(65.dp),
            value = from.value,
            onValueChange = {
                from.value = it
            },
            enabled = true,
            readOnly = false,
            textStyle = TextStyle.Default.copy(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = {
                Text(
                    text = "Destination",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Explore,
                    contentDescription = "Destination"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedLabelColor = AppGray,
                focusedLabelColor = Color.Black,
                cursorColor = AppDarkBlue,
                trailingIconColor = AppDarkBlue
            ),
            shape = RoundedCornerShape(7.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            singleLine = true
        )
    }

    Spacer(modifier = Modifier.height(20.dp))


}


@Composable
private fun FlightTicketMainPart() {

}

@Preview
@Composable
fun DatePicker() {
    var dateSelector by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
                .format(dateSelector)
        }
    }

    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Date Picker")
        }

        Text(
            text = formattedDate,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(),
        shape = RoundedCornerShape(12.dp),
        buttons = {
            positiveButton(text = "Select")
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now(),
            title = "Pick date",
        ) {
            dateSelector = it
        }
    }
}