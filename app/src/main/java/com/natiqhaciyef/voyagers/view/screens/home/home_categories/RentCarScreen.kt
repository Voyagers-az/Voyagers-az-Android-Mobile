package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.voyagers.util.CarBrands
import com.natiqhaciyef.voyagers.util.CityList
import com.natiqhaciyef.voyagers.util.CurrencyList
import com.natiqhaciyef.voyagers.view.components.CustomDropDownMenu
import com.natiqhaciyef.voyagers.view.ui.theme.*

@Preview
@Composable
fun RentCarScreen() {
    val currency = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val minPrice = remember { mutableStateOf(0.0) }
    val maxPrice = remember { mutableStateOf(0.0) }
    val dayCount = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(AppAquatic)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            RentCarTopView(currency, brand, city, minPrice, maxPrice, dayCount)
        }
    }
}


@Preview
@Composable
private fun RentCarTopView(
    currency: MutableState<String> = mutableStateOf(""),
    brand: MutableState<String> = mutableStateOf(""),
    city: MutableState<String> = mutableStateOf(""),
    minPrice: MutableState<Double> = mutableStateOf(0.0),
    maxPrice: MutableState<Double> = mutableStateOf(0.0),
    dayCount: MutableState<Int> = mutableStateOf(0),
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        CustomDropDownMenu(
            name = "Marka",
            width = 225.dp,
            padding = 10.dp,
            list = CarBrands.list,
            selectedOption = brand
        )
        CustomDropDownMenu(
            name = "Şəhər",
            width = 225.dp,
            padding = 10.dp,
            list = CityList.list,
            selectedOption = city
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 5.dp)
                .width(115.dp)
                .height(60.dp),
            value = if (minPrice.value > 0.0) "${minPrice.value}" else "",
            onValueChange = {
                minPrice.value = it.toDouble()
            },
            label = {
                Text(
                    text = "Min qiymət",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = AppGray
                )
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            enabled = true,
            readOnly = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                cursorColor = AppDarkBlue,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Black,
                textColor = if (minPrice.value > 0) Color.Black else AppGray
            ),
            shape = RoundedCornerShape(7.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 5.dp)
                .width(115.dp)
                .height(60.dp),
            value = if (maxPrice.value > 0.0) "${maxPrice.value}" else "",
            onValueChange = {
                maxPrice.value = it.toDouble()
            },
            label = {
                Text(
                    text = "Max qiymət",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = AppGray
                )
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            enabled = true,
            readOnly = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                cursorColor = AppDarkBlue,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Black,
                textColor = if (maxPrice.value > 0) Color.Black else AppGray
            ),
            shape = RoundedCornerShape(7.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        CurrencyDropDownMenu(
            name = "AZN",
            width = 120.dp,
            padding = 8.dp,
            list = CurrencyList.list,
            selectedOption = currency
        )

        Spacer(modifier = Modifier.width(10.dp))
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier,
            value = "${dayCount.value}",
            onValueChange = {
                dayCount.value = it.toInt()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Black,
                cursorColor = AppDarkBlue
            ),
            textStyle = TextStyle.Default.copy(),
            label = {

            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal
            ),
            enabled = true,
            readOnly = false,
            singleLine = true,
            shape = RoundedCornerShape(7.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = ""
                )
            }
        )

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
