package com.natiqhaciyef.voyagers.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.voyagers.data.model.TicketInfoModel
import com.natiqhaciyef.voyagers.data.model.TicketModel
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.functions.fromDoubleToTimeLine
import com.natiqhaciyef.voyagers.view.ui.theme.*

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
                    text = "${ticketInfoModel.ticketModel.fromCity}, ${ticketInfoModel.ticketModel.fromCountry}",
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
                TicketDepArrView(ticketInfoModel.ticketModel)

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
    ticketModel: TicketModel = DefaultModelImplementations.ticketInfoModel.ticketModel
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