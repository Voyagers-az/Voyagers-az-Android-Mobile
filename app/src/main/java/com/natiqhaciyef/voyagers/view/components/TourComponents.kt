package com.natiqhaciyef.voyagers.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.tour.CampModel
import com.natiqhaciyef.voyagers.data.model.tour.PlaceModel
import com.natiqhaciyef.voyagers.data.model.tour.TourModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue

//@Preview
@Composable
fun TourCardItem(
    tourModel: TourModel = DefaultModelImplementations.tourModel,
    isLoading: MutableState<Boolean> = mutableStateOf(true),
    content: (Any) -> Unit = { }
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
            .width(280.dp)
            .height(330.dp)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
            .clickable {
                content(
                    tourModel
                )
            },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center),
                    color = AppDarkBlue,
                    strokeWidth = 3.dp
                )
            }
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


//@Preview
@Composable
fun CampCardItem(
    campModel: CampModel = DefaultModelImplementations.campModel,
    isLoading: MutableState<Boolean> = mutableStateOf(true),
    content: (Any) -> Unit = { }
) {
    val colorMatrix = floatArrayOf(
        0.7f, 0f, 0f, 0f, 0f,
        0f, 0.7f, 0f, 0f, 0f,
        0f, 0f, 0.7f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    val price = "%.2f".format(campModel.price)

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(330.dp)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
            .clickable {
                content(
                    campModel
                )
            },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center),
                    color = AppDarkBlue,
                    strokeWidth = 3.dp
                )
            }
        }

        Image(
            painter = rememberImagePainter(data = campModel.image),
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
                    text = campModel.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(5.dp))

                RatingBar(rating = campModel.rating)

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

//@Preview
@Composable
fun PlaceItem(
    item: PlaceModel = DefaultModelImplementations.place,
    isLoading: MutableState<Boolean> = mutableStateOf(true)
) {

    val colorMatrix = floatArrayOf(
        0.7f, 0f, 0f, 0f, 0f,
        0f, 0.7f, 0f, 0f, 0f,
        0f, 0f, 0.7f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    Box(
        modifier = Modifier
            .width(280.dp)
            .height(330.dp)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
    ) {

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center),
                    color = AppDarkBlue,
                    strokeWidth = 3.dp
                )
            }
        }

        Image(
            painter = rememberImagePainter(data = item.image[0]),
            contentDescription = "Place image",
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Image(
            painter = rememberImagePainter(data = item.image[1]),
            contentDescription = "Place map",
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(5.dp))

            RatingBar(rating = item.rating)

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


