package com.natiqhaciyef.voyagers.view.screens.home.main.tours

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.TourScope
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.util.obj.FontList
import com.natiqhaciyef.voyagers.view.components.CampCardItem
import com.natiqhaciyef.voyagers.view.components.TourCardItem
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.natiqhaciyef.voyagers.view.viewmodel.tour.TourViewModel

//@Preview
@Composable
fun TourScreen(
    viewModel: TourViewModel = hiltViewModel(),
    navController: NavController
) {
    val search = remember { mutableStateOf("") }
    val tours = viewModel.toursList
    val camps = viewModel.campList
    val filterState = remember { mutableStateOf(false) }
    val maxPrice = remember { mutableStateOf(0) }
    val minPrice = remember { mutableStateOf(0) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(if (filterState.value) 370.dp else 210.dp)
                .height(200.dp)
                .background(AppAquatic)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            TourTopView(search, filterState, maxPrice, minPrice)
            TourMainView(search, navController, tours, camps)
        }
    }
}

//@Preview
@Composable
fun TourTopView(
    search: MutableState<String> = mutableStateOf(""),
    filterState: MutableState<Boolean>,
    maxPrice: MutableState<Int>,
    minPrice: MutableState<Int>
) {

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            text = "Axtarış bölməsi",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 25.dp),
            value = search.value,
            onValueChange = {
                search.value = it
            },
            placeholder = {
                Text(text = "Turlar üçün axtarış")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = true,
            readOnly = false,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "Filter icon",
                    modifier = Modifier.clickable {
                        filterState.value = !filterState.value
                    }
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )

//        if (filterState.value) {
//            Spacer(modifier = Modifier.height(5.dp))
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(170.dp)
//                    .padding(horizontal = 25.dp)
//                    .background(Color.White),
//            ) {
//                Spacer(modifier = Modifier.height(5.dp))
//            }
//        } else {
//            Spacer(modifier = Modifier.height(15.dp))
//        }
    }
}

@Composable
fun TourMainView(
    search: MutableState<String> = mutableStateOf(""),
    navController: NavController,
    tours: MutableState<List<TourModel>> = mutableStateOf(mutableListOf()),
    camps: MutableState<List<CampModel>> = mutableStateOf(mutableListOf())
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            text = "Yerli turlar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // create local tour model & viewModel of TourScreen
        Spacer(modifier = Modifier.height(10.dp))
        val localTours = tours.value
            .filter { it.scope == TourScope.Local.scope }
            .filter {
                it.name.lowercase().contains(search.value.lowercase())
                        || it.location.lowercase().contains(search.value.lowercase())
                        || it.region.lowercase().contains(search.value.lowercase())
            }

        if (localTours.isEmpty()) {
            Text(
                text = "Tur tapılmadı",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontList.fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow {
                items(localTours) { tour ->
                    Spacer(modifier = Modifier.width(5.dp))
                    TourCardItem(tour) {
                        DefaultModelImplementations.data = tour
                        navController.navigate(ScreenID.TourDetails.name)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }

        // create global tour model & viewModel of TourScreen
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Xarici turlar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // create local tour model & viewModel of TourScreen
        Spacer(modifier = Modifier.height(10.dp))
        val globalTours = tours.value
            .filter { it.scope == TourScope.Global.scope }
            .filter {
                it.name.lowercase().contains(search.value.lowercase())
                        || it.location.lowercase().contains(search.value.lowercase())
                        || it.region.lowercase().contains(search.value.lowercase())
            }

        if (globalTours.isEmpty()) {
            Text(
                text = "Tur tapılmadı",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontList.fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow {
                items(globalTours) { tour ->
                    Spacer(modifier = Modifier.width(5.dp))
                    TourCardItem(tour) {
                        DefaultModelImplementations.data = tour
                        navController.navigate(ScreenID.TourDetails.name)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }



        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Düşərgələr",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // create local tour model & viewModel of TourScreen
        Spacer(modifier = Modifier.height(10.dp))
        val campList = camps.value
            .filter {
                it.name.lowercase().contains(search.value.lowercase())
                        || it.location.lowercase().contains(search.value.lowercase())
                        || it.region.lowercase().contains(search.value.lowercase())
            }

        if (campList.isEmpty()) {
            Text(
                text = "Düşərgə tapılmadı",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontList.fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow {
                items(campList) { camp ->
                    Spacer(modifier = Modifier.width(5.dp))
                    CampCardItem(camp) {
                        DefaultModelImplementations.data = camp
                        navController.navigate(ScreenID.TourDetails.name)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(65.dp))
    }
}

// filter parameters
// camplarin teskil olunmasi
