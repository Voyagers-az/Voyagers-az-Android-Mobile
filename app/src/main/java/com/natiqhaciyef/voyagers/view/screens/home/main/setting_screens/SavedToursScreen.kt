package com.natiqhaciyef.voyagers.view.screens.home.main.setting_screens

import android.graphics.drawable.GradientDrawable
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.SwipeDirection
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.components.RatingBar
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.tour.TourDetailsViewModel

@Preview
@Composable
fun SavedToursScreen(
    tourDetailsViewModel: TourDetailsViewModel = hiltViewModel()
) {
    val savedTours = remember { tourDetailsViewModel.savedTours }
    val isDeleted = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(AppDarkBlue)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 60.dp),
                text = "Bəyənilən tur və düşərgələr",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(55.dp))
            SavedToursMainPart(savedTours, tourDetailsViewModel, isDeleted)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun SavedToursMainPart(
    list: MutableState<MutableList<TourModel>>,
    viewModel: TourDetailsViewModel,
    isDeleted: MutableState<Boolean>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list.value) { tourModel ->
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        viewModel.deleteTourModel(tourModel)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart),
            ) {
                TourCardComponent(
                    tourModel = tourModel,
                )
            }

        }
    }
}

@Composable
private fun TourCardComponent(
    tourModel: TourModel,
) {
    val colorMatrix = floatArrayOf(
        0.7f, 0f, 0f, 0f, 0f,
        0f, 0.7f, 0f, 0f, 0f,
        0f, 0f, 0.7f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    val price = "%.2f".format(tourModel.price)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {


        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
                color = AppDarkBlue,
                strokeWidth = 3.dp
            )
        }


        Image(
            painter = rememberImagePainter(data = tourModel.image[0]),
            contentDescription = "Tour image",
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .height(45.dp)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .align(Alignment.TopEnd),
            ) {
                Text(
                    modifier = Modifier
                        .background(AppDarkBlue),
                    text = "$price AZN",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {


                Text(
                    text = tourModel.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(5.dp))

                RatingBar(rating = tourModel.rating)

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}
