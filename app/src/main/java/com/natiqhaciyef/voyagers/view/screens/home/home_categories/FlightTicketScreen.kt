package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple

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
}


@Composable
private fun FlightTicketMainPart() {

}