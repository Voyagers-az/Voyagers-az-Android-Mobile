package com.natiqhaciyef.voyagers.view.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DepartureBoard
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
import com.natiqhaciyef.voyagers.util.FontList
import com.natiqhaciyef.voyagers.view.components.CategoryCardView
import com.natiqhaciyef.voyagers.view.components.CurvedRectangle
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppBrown
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppYellow

@Preview
@Composable
fun HomeScreen() {
    val list = mutableListOf(
        Icons.Default.DirectionsCar,
        Icons.Default.DepartureBoard,
        Icons.Default.Train
    )
    val selectedCategory = remember { mutableStateOf(Icons.Default.DirectionsCar) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Surface {
            CurvedRectangle(320)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                HomeTopView(selectedCategory, list)
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
                .padding(start = 40.dp, end = 60.dp),
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
                    append(text = "Welcome back, ")
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
                    append(text = "\nLet's start to choose trip location")
                }
            }
        )


        Spacer(modifier = Modifier.height(85.dp))

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

@Preview
@Composable
fun HomeMainPartView() {


}
