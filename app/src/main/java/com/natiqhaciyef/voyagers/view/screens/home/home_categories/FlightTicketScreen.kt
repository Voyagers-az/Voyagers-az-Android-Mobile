package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.data.model.TicketModel
import com.natiqhaciyef.voyagers.view.components.DatePicker1
import com.natiqhaciyef.voyagers.view.components.DatePicker2
import com.natiqhaciyef.voyagers.view.components.TicketCardView
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel.FlightTicketViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun FlightTicketScreen(
    viewModel: FlightTicketViewModel = hiltViewModel()
) {
    val from = remember { mutableStateOf("") }
    val to = remember { mutableStateOf("") }
    val dateFrom = remember { mutableStateOf("") }
    val dateTo = remember { mutableStateOf("") }
    val ticketsList = remember { viewModel.ticketList }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (dateFrom.value.isNotEmpty() || dateTo.value.isNotEmpty() || from.value.isNotEmpty() || to.value.isNotEmpty()) {
                FloatingActionButton(
                    onClick = {
                        to.value = ""
                        from.value = ""
                        dateFrom.value = ""
                        dateTo.value = ""
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppWhiteLightPurple)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(255.dp)
                    .background(AppAquatic),
            )
            Column(
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Avia biletlərin online alınması platforması",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlightTicketTopView(
                    from = from,
                    to = to,
                    dateFrom = dateFrom,
                    dateTo = dateTo
                )

                Spacer(modifier = Modifier.height(40.dp))

                FlightTicketMainPart(
                    from = from,
                    to = to,
                    dateFrom = dateFrom,
                    dateTo = dateTo,
                    ticketsList = ticketsList
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
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
                fontSize = 15.sp,
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

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        DatePicker1(dateFrom = dateFrom)

        Spacer(modifier = Modifier.width(20.dp))

        DatePicker2(dateTo = dateTo)
    }
}


@Composable
private fun FlightTicketMainPart(
    from: MutableState<String> = mutableStateOf(""),
    to: MutableState<String> = mutableStateOf(""),
    dateFrom: MutableState<String> = mutableStateOf(""),
    dateTo: MutableState<String> = mutableStateOf(""),
    ticketsList: MutableState<List<TicketModel>>
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

    val tickets = ticketsList.value
        .filter { it.toCity.contains(to.value) || it.toCountry.contains(to.value) }
        .filter { it.fromCity.contains(from.value) || it.fromCountry.contains(from.value) }
        .filter { (it.departureDate.contains(dateFrom.value) || it.arrivalDate.contains(dateTo.value)) }

    LazyColumn {
        items(tickets) { ticket ->
            // Ticket view without users creating

            TicketCardView(ticketModel = ticket)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

