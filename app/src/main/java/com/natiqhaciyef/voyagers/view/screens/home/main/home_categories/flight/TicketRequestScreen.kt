package com.natiqhaciyef.voyagers.view.screens.home.main.home_categories.flight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel
import com.natiqhaciyef.voyagers.util.functions.dateToLocalTime
import com.natiqhaciyef.voyagers.util.functions.fromDateToDay
import com.natiqhaciyef.voyagers.util.functions.fromDoubleToTimeLine
import com.natiqhaciyef.voyagers.util.functions.splitterTimeDate
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.obj.FontList
import com.natiqhaciyef.voyagers.view.components.TicketItem
import com.natiqhaciyef.voyagers.view.navigation.FlightTicketData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple

@Preview
@Composable
fun TicketRequestScreen(
    ticketModel: TicketModel = DefaultModelImplementations.ticketModel
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.calculateTopPadding()

        TicketRequestTopView(ticketModel = ticketModel)
        TicketRequestMainPart(ticketModel = ticketModel)
    }
}


@Composable
private fun TicketRequestTopView(ticketModel: TicketModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroun_globe),
            contentDescription = "World map",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.25f),
        )

    }
}


@Composable
private fun TicketRequestMainPart(ticketModel: TicketModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth()
                .height(320.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp
        ) {
            Image(
                painter = rememberImagePainter(data = ticketModel.image),
                contentDescription = "Ticket model",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.Flight,
                contentDescription = "Flight",
                modifier = Modifier
                    .padding(start = 20.dp, top = 4.dp)
                    .size(30.dp)
                    .rotate(45f),
                tint = AppDarkBlue
            )
            if (ticketModel.transfer != null) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 20.dp),
                    fontFamily = FontList.fontFamily2,
                    text = "${ticketModel.companyNames[0]} hava yolları : ${ticketModel.fromCity} - ${ticketModel.transfer!!.landedPlace} - ${ticketModel.toCity}",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    fontFamily = FontList.fontFamily2,
                    text = "${ticketModel.companyNames[0]} hava yolları : ${ticketModel.fromCity} - ${ticketModel.toCity}",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }


        val depTime = splitterTimeDate(ticketModel.departureDate)["time"]
        val depDate = splitterTimeDate(ticketModel.departureDate)["date"]

        val formattedDepTimeDate = dateToLocalTime(depDate!!)

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Start,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = AppDarkBlue,
                                fontSize = 18.sp,
                                fontFamily = FontList.fontFamily2,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("Gediş: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("$formattedDepTimeDate, 2023")
                        }
                    }
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
                    textAlign = TextAlign.Start,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = AppDarkBlue,
                                fontSize = 18.sp,
                                fontFamily = FontList.fontFamily2,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("Saat: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("$depTime")
                        }
                    }
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                textAlign = TextAlign.End,
                text = "Qiymət: \n${ticketModel.price.toInt()} AZN",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontList.fontFamily2
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        Ticket(ticketModel = FlightTicketData.depTicket!!)
        Ticket(ticketModel = FlightTicketData.retTicket!!)
    }
}


@Composable
private fun Ticket(
    ticketModel: TicketModel = DefaultModelImplementations.ticketInfoModel.depTicketModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp),
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
