package com.natiqhaciyef.voyagers.view.screens.home

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.TourScope
import com.natiqhaciyef.voyagers.view.components.CampCardItem
import com.natiqhaciyef.voyagers.view.components.TourCardItem
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.natiqhaciyef.voyagers.view.viewmodel.TourViewModel

@Preview
@Composable
fun TourScreen(
    viewModel: TourViewModel = hiltViewModel()
) {
    val search = remember { mutableStateOf("") }
    val tours = viewModel.toursList
    val camps = viewModel.campList
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(AppAquatic)
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            TourTopView(search)
            TourMainView(search, tours, camps)
        }
    }
}

@Preview
@Composable
fun TourTopView(
    search: MutableState<String> = mutableStateOf("")
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(70.dp))

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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )

        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun TourMainView(
    search: MutableState<String> = mutableStateOf(""),
    tours: MutableState<List<TourModel>> = mutableStateOf(mutableListOf()),
    camps: MutableState<List<CampModel>> = mutableStateOf(mutableListOf())
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(55.dp))
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
        Spacer(modifier = Modifier.height(15.dp))
        val localTours = tours.value.filter { it.scope == TourScope.Local.scope }
        LazyRow{
            items(localTours){tour ->
                Spacer(modifier = Modifier.width(5.dp))
                TourCardItem(tour)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }

        // create global tour model & viewModel of TourScreen
        Spacer(modifier = Modifier.height(35.dp))

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
        Spacer(modifier = Modifier.height(15.dp))
        val globalTours = tours.value.filter { it.scope == TourScope.Global.scope }
        LazyRow{
            items(globalTours){tour ->
                Spacer(modifier = Modifier.width(5.dp))
                TourCardItem(tour)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }

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
        Spacer(modifier = Modifier.height(15.dp))
        val campList = camps.value
        LazyRow{
            items(campList){camp ->
                Spacer(modifier = Modifier.width(5.dp))
                CampCardItem(camp)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}


// task - tur filtirlemek
// filter parameters
// camplarin teskil olunmasi
//