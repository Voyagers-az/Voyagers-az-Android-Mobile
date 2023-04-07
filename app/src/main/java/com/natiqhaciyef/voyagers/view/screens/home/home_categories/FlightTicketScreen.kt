package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
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
import com.natiqhaciyef.voyagers.data.model.TicketModel
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.functions.dateToLocalTime
import com.natiqhaciyef.voyagers.view.components.TicketCardView
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
    val from = remember { mutableStateOf("") }
    val to = remember { mutableStateOf("") }
    val dateFrom = remember { mutableStateOf("") }
    val dateTo = remember { mutableStateOf("") }
    var ticketsList = remember { mutableStateOf<List<TicketModel>>(mutableListOf()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(AppAquatic),
        )
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            FlightTicketTopView(
                from = from,
                to = to,
                dateFrom = dateFrom,
                dateTo = dateTo
            )

            Spacer(modifier = Modifier.height(30.dp))

            FlightTicketMainPart(ticketsList)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


@Preview
@Composable
private fun FlightTicketTopView(
    from: MutableState<String> = mutableStateOf(""),
    to: MutableState<String> = mutableStateOf(""),
    dateFrom: MutableState<String> = mutableStateOf(""),
    dateTo: MutableState<String> = mutableStateOf(""),
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
                    text = "Məkanım",
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
            value = to.value,
            onValueChange = {
                to.value = it
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
                    text = "İstiqamət",
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

    Spacer(modifier = Modifier.height(30.dp))

    DatePicker(
        dateFrom = dateFrom,
        dateTo = dateTo
    )

}


@Composable
private fun FlightTicketMainPart(
    ticketsList: MutableState<List<TicketModel>> =
        mutableStateOf(mutableListOf(DefaultModelImplementations.ticketModel))
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        text = "Aktiv biletlər",
        color = Color.Black,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(5.dp))

    LazyColumn {
        items(ticketsList.value) { ticket ->
            // Ticket view without users creating
            TicketCardView(ticketModel = ticket)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview
@Composable
fun DatePicker(
    dateFrom: MutableState<String> = mutableStateOf(""),
    dateTo: MutableState<String> = mutableStateOf(""),
) {
    val list = remember {
        mutableStateListOf<String>()
    }

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

    val today by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
                .format(dateSelector)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    if (list.size < 2) {
        dateFrom.value = formattedDate
    } else {
        dateFrom.value = list[0]
        dateTo.value = list[1]
    }


    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .height(60.dp)
                .padding(start = 20.dp)
                .align(Alignment.CenterStart),
            enabled = true,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
            ),
            border = BorderStroke(
                width = 2.dp, color = AppDarkBlue
            ),
            shape = RoundedCornerShape(7.dp),
            onClick = {
                dateDialogState.show()
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = if (list.isNotEmpty()) dateToLocalTime(list[0]) else "Gediş",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    color = Color.Black,
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    tint = AppDarkBlue,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }
        }



        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .height(60.dp)
                .padding(end = 20.dp)
                .align(Alignment.CenterEnd),
            enabled = true,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
            ),
            border = BorderStroke(
                width = 2.dp, color = AppDarkBlue
            ),
            shape = RoundedCornerShape(7.dp),
            onClick = {
                dateDialogState.show()
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = if (list.size >= 2) dateToLocalTime(list[1]) else "Dönüş",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterStart),
                    color = Color.Black,
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    tint = AppDarkBlue,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }
        }

        MaterialDialog(
            dialogState = dateDialogState,
            properties = DialogProperties(),
            shape = RoundedCornerShape(12.dp),
            buttons = {
                positiveButton(text = "Select")
            },
            backgroundColor = AppWhiteLightPurple,
        ) {
            this.datepicker(
                initialDate = LocalDate.now(),
                title = "Pick date",
                allowedDateValidator = {
                    it > dateSelector
                }
            ) {
                dateSelector = it
                list.add(formattedDate)
            }
        }
    }

}
