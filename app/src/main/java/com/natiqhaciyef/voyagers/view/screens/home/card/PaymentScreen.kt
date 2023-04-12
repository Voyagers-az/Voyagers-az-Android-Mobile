package com.natiqhaciyef.voyagers.view.screens.home.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.voyagers.data.model.PaymentChoiceModel
import com.natiqhaciyef.voyagers.util.PaymentMethodList
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple

@Preview
@Composable
fun PaymentScreen() {
    val visaSelected = remember { mutableStateOf(false) }
    val masterCardSelected = remember { mutableStateOf(false) }
    val paypalSelected = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
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
                    visaSelected.value = false
                    masterCardSelected.value = false
                    paypalSelected.value = true
                }
                Spacer(modifier = Modifier.height(8.dp))

            }

        }

        Button(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(220.dp)
                .height(60.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                if (visaSelected.value) {
                    // send visa details
                } else if (masterCardSelected.value) {
                    // send master card details
                } else if (paypalSelected.value) {
                    // send paypal details
                }
            }
        ) {
            Text(
                modifier = Modifier,
                text = "Təsdiq edin",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
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

