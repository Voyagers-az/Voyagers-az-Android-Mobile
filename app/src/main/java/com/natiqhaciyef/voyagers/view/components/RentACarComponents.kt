package com.natiqhaciyef.voyagers.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.CarRentModel
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    name: String,
    width: Dp,
    padding: Dp,
    list: List<String>,
    selectedOption: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(modifier = Modifier
        .width(width)
        .padding(padding)
        .border(
            1.dp, AppDarkBlue,
            shape = RoundedCornerShape(10.dp)
        ),
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = selectedOption.value,
            onValueChange = { },
            textStyle = TextStyle.Default.copy(
                color = AppDarkBlue,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            readOnly = true,
            label = {
                Text(
                    text = name,
                    color = AppGray,
                    fontWeight = FontWeight.SemiBold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = AppDarkBlue
            )
        )

        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    }) {
                    Text(
                        text = option,
                        color = AppDarkBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyDropDownMenu(
    name: String,
    width: Dp,
    padding: Dp,
    list: List<String>,
    selectedOption: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(modifier = Modifier
        .width(width)
        .height(60.dp)
        .padding(top = padding)
        .border(
            1.dp, AppDarkBlue,
            shape = RoundedCornerShape(10.dp)
        ),
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(7.dp)),
            value = selectedOption.value,
            onValueChange = { },
            textStyle = TextStyle.Default.copy(
                color = AppDarkBlue,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            readOnly = true,
            label = {
                Text(
                    text = name,
                    color = AppGray,
                    fontWeight = FontWeight.SemiBold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = AppDarkBlue
            )
        )

        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    }) {
                    Text(
                        text = option,
                        color = AppDarkBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}


@Preview
@Composable
fun CarCardItem(
    carRentModel: CarRentModel =
        DefaultModelImplementations.carRentModel
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(240.dp)
            .height(280.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .width(240.dp)
                .height(280.dp)
                .background(color = AppWhiteLightPurple)
        ) {
            Column(
                modifier = Modifier
                    .width(240.dp)
                    .height(280.dp)
            ) {
                Image(  // image format 5:4
                    painter = rememberImagePainter(data = carRentModel.car.image),
                    contentDescription = "Car Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 7.dp),
                    text = "${carRentModel.car.name} - ${carRentModel.car.brand}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp),
                    text = "Motor həcmi: ${carRentModel.car.engine}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 7.dp),
                        text = "İl: ${carRentModel.car.year}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        modifier = Modifier
                            .padding(start = 7.dp),
                        text = "Məkan: ${carRentModel.place}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }



                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp),
                    text = "Müddət: ${carRentModel.time}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp),
                    text = "Günlük qiyməti: ${carRentModel.dailyPrice.toInt()} ${carRentModel.priceType}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

            }
        }
    }
}