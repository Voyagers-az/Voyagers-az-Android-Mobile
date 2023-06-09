package com.natiqhaciyef.voyagers.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.flight.TicketInfoModel
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel
import com.natiqhaciyef.voyagers.util.functions.dateToLocalTime
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.functions.fromDoubleToTimeLine
import com.natiqhaciyef.voyagers.util.functions.splitterTimeDate
import com.natiqhaciyef.voyagers.view.navigation.FlightTicketData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//@Preview
@Composable
fun TicketDepArrView(
    ticketModel: TicketModel // = DefaultModelImplementations.ticketInfoModel.ticketModel
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = ticketModel.fromCity,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Start
        )


        Icon(
            imageVector = Icons.Default.FlightTakeoff,
            contentDescription = "Flight Icon",
            tint = AppYellow,
            modifier = Modifier
                .padding(end = 5.dp)
                .align(Alignment.End)
                .size(30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = ticketModel.departureDate,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = AppLightGray,
            textAlign = TextAlign.Start
        )

    }

    Spacer(modifier = Modifier.width(15.dp))

    Text(
        text = fromDoubleToTimeLine(ticketModel.flightTime),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 15.dp),
        color = AppYellow
    )

    Spacer(modifier = Modifier.width(15.dp))

    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = ticketModel.toCity,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.End
        )


        Icon(
            imageVector = Icons.Default.FlightLand,
            contentDescription = "Flight Icon",
            tint = AppYellow,
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.Start)
                .size(30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = ticketModel.arrivalDate,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = AppLightGray,
            textAlign = TextAlign.End
        )

    }
}


@Preview
@Composable
fun TicketCardViewWithUserInfo(
    ticketInfoModel: TicketInfoModel = DefaultModelImplementations.ticketInfoModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color(0xffE8F2F6))
            .padding(start = 15.dp, end = 15.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Flight,
            contentDescription = "Direction",
            tint = AppLightGray,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)
                .align(Alignment.TopCenter),
        )

        Icon(
            imageVector = Icons.Default.AirplaneTicket,
            contentDescription = "AirLine icon",
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp)
                .size(45.dp)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ad və Soyad : ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(0.75f),
                    text = "${ticketInfoModel.userInfo.name} ${ticketInfoModel.userInfo.surname}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Doğum tarixi : ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(0.75f),
                    text = "${ticketInfoModel.userInfo.dateOfBirth}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "FIN kod : ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    text = "${ticketInfoModel.userInfo.idNumber}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Location : ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(0.82f),
                    text = "${ticketInfoModel.depTicketModel.fromCity}, ${ticketInfoModel.depTicketModel.fromCountry}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TicketDepArrView(ticketInfoModel.depTicketModel)

            }
        }

        Icon(
            imageVector = Icons.Default.Alarm,
            contentDescription = "Direction",
            tint = Color.Gray,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(45.dp)
                .align(Alignment.BottomCenter),
        )
    }
}


@Preview
@Composable
fun TicketCardView(
    ticketModel: TicketModel = DefaultModelImplementations.ticketInfoModel.depTicketModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = AppDarkBlue)
            .padding(start = 15.dp, end = 15.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Flight,
            contentDescription = "Direction",
            tint = AppDarkBlueLight,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)
                .align(Alignment.TopCenter),
        )

        Icon(
            imageVector = Icons.Default.AirplaneTicket,
            contentDescription = "AirLine icon",
            tint = AppAquaticLight,
            modifier = Modifier
                .padding(top = 3.dp, end = 3.dp)
                .size(45.dp)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TicketDepArrView(ticketModel = ticketModel)
            }
        }

        Icon(
            imageVector = Icons.Default.Alarm,
            contentDescription = "Direction",
            tint = AppYellow,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(45.dp)
                .align(Alignment.BottomCenter),
        )
    }
}


@Composable
fun TicketItem(
    ticketModel: TicketModel = DefaultModelImplementations.ticketInfoModel.depTicketModel,
    navController: NavController = rememberNavController()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp)
            .clickable {
                navController.navigate(ScreenID.TicketRequest.name)
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.item_flight_png),
                contentDescription = "Flight line",
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = "Flight",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.Center)
                    .rotate(90f)
                    .size(40.dp),
                tint = Color(0xff008FA0)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .align(Alignment.TopStart),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 7.dp),
                    text = "Uçuş bileti : ${dateToLocalTime(splitterTimeDate(ticketModel.departureDate)["date"]!!)}",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 15.dp, bottom = 25.dp),
                text = splitterTimeDate(ticketModel.departureDate)["time"]!!,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )


            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp, bottom = 25.dp),
                text = splitterTimeDate(ticketModel.arrivalDate)["time"]!!,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )


            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15.dp, bottom = 20.dp),
                text = ticketModel.fromCity,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp),
                text = fromDoubleToTimeLine(DefaultModelImplementations.ticketInfoModel.depTicketModel.flightTime),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 15.dp, bottom = 20.dp),
                text = ticketModel.toCity,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

        }
    }
}

@Preview
@Composable
fun DatePicker1(
    dateFrom: MutableState<String> = mutableStateOf(""),
) {
    val state = remember { mutableStateOf(false) }

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
    if (state.value)
        dateFrom.value = formattedDate

    OutlinedButton(
        modifier = Modifier
            .width(160.dp)
            .height(60.dp),
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
                text = if (dateFrom.value.isNotEmpty() && state.value) dateFrom.value else "Gediş",
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
                state.value = true
            }
        }
    }

}


@Preview
@Composable
fun DatePicker2(
    dateTo: MutableState<String> = mutableStateOf(""),
) {
    val state = remember { mutableStateOf(false) }
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
    if (state.value)
        dateTo.value = formattedDate


    OutlinedButton(
        modifier = Modifier
            .width(160.dp)
            .height(60.dp),
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
                text = if (dateTo.value.isNotEmpty() && state.value) dateTo.value else "Gediş",
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
                state.value = true
            }
        }
    }
}
