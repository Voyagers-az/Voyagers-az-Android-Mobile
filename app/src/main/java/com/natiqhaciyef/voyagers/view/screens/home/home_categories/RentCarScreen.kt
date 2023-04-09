package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.data.model.CarModel
import com.natiqhaciyef.voyagers.data.model.CarRentModel
import com.natiqhaciyef.voyagers.util.CarBrands
import com.natiqhaciyef.voyagers.util.CityList
import com.natiqhaciyef.voyagers.util.CurrencyList
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
    val maxPrice = remember { mutableStateOf(0.0) }
    val dayCount = remember { mutableStateOf(0) }
    val list = remember { viewModel.carsList }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(AppAquatic)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            RentCarTopView(currency, brand, city, minPrice, maxPrice, dayCount)
            Spacer(modifier = Modifier.height(30.dp))
            RentCarMainPart(list)
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
}


@Composable
fun RentCarMainPart(
    list: MutableState<List<CarRentModel>>
){
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 125.dp)){
        items(list.value) { car ->
            // car model
        }
    }
}



