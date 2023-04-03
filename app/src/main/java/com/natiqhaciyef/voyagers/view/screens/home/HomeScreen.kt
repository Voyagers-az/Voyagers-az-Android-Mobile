package com.natiqhaciyef.voyagers.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.natiqhaciyef.voyagers.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.util.CategoryIcons
import com.natiqhaciyef.voyagers.util.ContactList
import com.natiqhaciyef.voyagers.util.Services
import com.natiqhaciyef.voyagers.view.components.*
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.natiqhaciyef.voyagers.view.viewmodel.HomeViewModel

//@Preview
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val list = CategoryIcons.list
    val places = viewModel.placesList
    val selectedCategory = remember { mutableStateOf(Icons.Default.DirectionsCar) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            color = AppWhiteLightPurple
        ) {
            CurvedRectangle(300)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                HomeTopView(selectedCategory, list)
                HomeMainPartView(places, viewModel.isLoading, navController)
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Preview
@Composable
fun HomeTopView(
    selectedCategory: MutableState<ImageVector> = mutableStateOf(Icons.Default.DirectionsCar),
    list: MutableList<ImageVector> = mutableListOf()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(75.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            textAlign = TextAlign.Start,
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(
                            listOf(Font(resId = R.font.rubik_bold))
                        ),
                        color = Color.White,
                    )
                ) {
                    append(text = "Xoş gəldin, ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                        fontFamily = FontFamily(
                            listOf(Font(resId = R.font.rubik_bold))
                        ),
                        fontWeight = FontWeight.Bold,
                        color = AppDarkBlue
                    )
                ) {
                    append(text = "Natiq")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(
                            listOf(Font(resId = R.font.rubik_bold))
                        ),
                        color = Color.White,
                    )
                ) {
                    append(text = "\nSəyahət üçün məkan seç və dünya turuna başla")
                }
            }
        )


        Spacer(modifier = Modifier.height(65.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(list) { item ->
                Spacer(modifier = Modifier.width(25.dp))
                CategoryCardView(item)
                Spacer(modifier = Modifier.width(25.dp))
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
//@Preview
@Composable
fun HomeMainPartView(
    list: MutableState<List<PlaceModel>> = mutableStateOf(mutableListOf()),
    isLoading: MutableState<Boolean> = mutableStateOf(true),
    navController: NavController
) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Məkanlar",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(list.value) { place ->
                PlaceItem(place, isLoading) // implement it
            }
        }


        Spacer(modifier = Modifier.height(15.dp))
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Əlaqə",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ContactCardItem(contactModel = ContactList.list[0])
            Spacer(modifier = Modifier.width(30.dp))
            ContactCardItem(contactModel = ContactList.list[1])
            Spacer(modifier = Modifier.width(30.dp))
            ContactCardItem(contactModel = ContactList.list[2])
        }
    }
}
