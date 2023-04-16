package com.natiqhaciyef.voyagers.view.screens.home.main.home_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.data.model.CarRentModel
import com.natiqhaciyef.voyagers.util.obj.CarBrands
import com.natiqhaciyef.voyagers.util.obj.CityList
import com.natiqhaciyef.voyagers.util.obj.CurrencyList
import com.natiqhaciyef.voyagers.view.components.CarCardItem
import com.natiqhaciyef.voyagers.view.components.CurrencyDropDownMenu
import com.natiqhaciyef.voyagers.view.components.CustomDropDownMenu
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel.RentCarViewModel

@Preview
@Composable
fun RentCarScreen(
    viewModel: RentCarViewModel = hiltViewModel()
) {
    val currency = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val minPrice = remember { mutableStateOf(0.0) }
    val maxPrice = remember { mutableStateOf(10000.0) }
    val list = remember { viewModel.carsList }

    Scaffold(
        floatingActionButton = {
            if (brand.value.isNotEmpty() || city.value.isNotEmpty() || minPrice.value != 0.0 || maxPrice.value != 10000.0) {
                FloatingActionButton(
                    onClick = {
                        brand.value = ""
                        city.value = ""
                        minPrice.value = 0.0
                        maxPrice.value = 10000.0
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
                    .height(270.dp)
                    .background(AppAquatic)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "Avtomobil və ya digər nəqliyyat növlərinin icarələnməsi",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                RentCarTopView(currency, brand, city, minPrice, maxPrice)
                Spacer(modifier = Modifier.height(25.dp))
                RentCarMainPart(list, currency, brand, city, minPrice, maxPrice)
            }
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
            value = if (maxPrice.value != 0.0 && maxPrice.value != 10000.0) "${maxPrice.value}" else "",
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
}


@Composable
fun RentCarMainPart(
    list: MutableState<List<CarRentModel>>,
    currency: MutableState<String>,
    brand: MutableState<String>,
    city: MutableState<String>,
    minPrice: MutableState<Double>,
    maxPrice: MutableState<Double>,
) {
    val cars = list.value
        .filter { it.car.brand.contains(brand.value) || it.car.name.contains(brand.value) }
        .filter { it.place.contains(city.value) }
        .filter { it.priceType.contains(currency.value) }
        .filter {
            if (minPrice.value.toInt() != 0 || maxPrice.value.toInt() != 0) {
                minPrice.value.toInt() <= it.dailyPrice
                        && maxPrice.value.toInt() >= it.dailyPrice
            } else
                true
        }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(cars) { car ->
            CarCardItem(car)
        }
    }
}



