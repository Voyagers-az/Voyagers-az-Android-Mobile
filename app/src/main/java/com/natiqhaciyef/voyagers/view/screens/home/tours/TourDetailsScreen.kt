package com.natiqhaciyef.voyagers.view.screens.home.tours

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.util.classes.DataTypes
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.view.viewmodel.tour.TourDetailsViewModel
import com.natiqhaciyef.voyagers.util.FontList
import com.natiqhaciyef.voyagers.view.components.RatingBar
import com.natiqhaciyef.voyagers.view.ui.theme.DarkYellow


//@Preview
@Composable
fun TourDetailsScreen(
    data: Any = Any(),
    viewModel: TourDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val item = viewModel.dataTypeCaster(data)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(AppAquatic)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TourDetailsTopView(item, data, navController)
            TourDetailsMainView(data)
        }
    }
}


@Composable
fun TourDetailsTopView(
    type: DataTypes = DataTypes.PlaceModel,
    data: Any = Any(),
    navController: NavController
) {
    val colorMatrix = floatArrayOf(
        0.8f, 0f, 0f, 0f, 0f,
        0f, 0.85f, 0f, 0f, 0f,
        0f, 0f, 0.9f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

//    Spacer(modifier = Modifier.height(30.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = 10.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (type == DataTypes.TourModel) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainter(data = (data as TourModel).image[0]),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            } else if (type == DataTypes.CampModel) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainter(data = (data as CampModel).image),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardBackspace,
                contentDescription = "Go back icon",
                tint = AppWhiteLightPurple,
                modifier = Modifier
                    .padding(start = 15.dp, top = 35.dp)
                    .size(40.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 15.dp, end = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Like",
                    modifier = Modifier
                        .size(30.dp),
                    tint = AppWhiteLightPurple
                )

                Spacer(modifier = Modifier.width(30.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardCommandKey,
                    contentDescription = "Share",
                    tint = AppWhiteLightPurple,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TourDetailsMainView(data: Any = Any()) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                text = if (data is TourModel) data.name else if (data is CampModel) data.name else "Information",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    rating = if (data is TourModel) data.rating else if (data is CampModel) data.rating else 0.0,
                    starsColor = DarkYellow
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "(${if (data is TourModel) data.rating else if (data is CampModel) data.rating else 0.0})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.PinDrop,
                    contentDescription = "Location",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(23.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = if (data is TourModel) data.location else if (data is CampModel) data.location else "Təyinat məntəqəsi qeyd olunmayıb",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = if (data is TourModel) "${data.info}" else if (data is CampModel) data.info else "",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppDarkBlue,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 25.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(55.dp),
            onClick = {
                // go to cart and details screen
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Müraciət et",
                color = AppWhiteLightPurple,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                fontFamily = FontList.fontFamily
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}