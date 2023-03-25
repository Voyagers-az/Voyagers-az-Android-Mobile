package com.natiqhaciyef.voyagers.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.natiqhaciyef.voyagers.data.model.ServiceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourScope
import com.natiqhaciyef.voyagers.util.CategoryIcons
import com.natiqhaciyef.voyagers.util.Services
import com.natiqhaciyef.voyagers.view.components.*
import com.natiqhaciyef.voyagers.view.ui.theme.*

@Preview
@Composable
fun HomeScreen() {
    val list = CategoryIcons.list
    val selectedCategory = remember { mutableStateOf(Icons.Default.DirectionsCar) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            color = AppWhiteLightPurple
        ) {
            CurvedRectangle(280)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                HomeTopView(selectedCategory, list)
                HomeMainPartView()
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
        Spacer(modifier = Modifier.height(55.dp))
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

val list = mutableListOf(
    TourModel(
        id = 1,
        name = "Avia",
        image = mutableListOf("https://img.fotocommunity.com/qebele-azerbaijan-6372766b-168f-4fea-891b-f2afc2981167.jpg?height=1080"),
        info = "Qəbələ turu. Gediş-gəliş, səhər yeməyi daxil",
        route = mutableMapOf("Baku" to "Qebele", "Qebele" to "Baku"),
        price = 20.0,
        personCount = 25,
        rating = 4.5,
        country = "Azerbaijan",
        scope = TourScope.Local.scope
    ),
    TourModel(
        id = 2,
        name = "Voyager",
        image = mutableListOf("https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg"),
        info = "Quba turu, Çənlibel gölü. Gediş-gəliş, səhər yeməyi daxil",
        route = mutableMapOf("Baku" to "Quba", "Quba" to "Baku"),
        price = 20.0,
        personCount = 25,
        rating = 4.5,
        country = "Azerbaijan",
        scope = TourScope.Local.scope
    )
)


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun HomeMainPartView() {
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
            items(list) { tour ->
                PlaceItem(tour) // implement it
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Xidmətlər",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        ServiceCardItem(serviceModel = Services.services[0])
        Spacer(modifier = Modifier.height(5.dp))
        ServiceCardItem(serviceModel = Services.services[1])
        Spacer(modifier = Modifier.height(5.dp))
        ServiceCardItem(serviceModel = Services.services[2])

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
    }
}
