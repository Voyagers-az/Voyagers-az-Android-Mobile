package com.natiqhaciyef.voyagers.view.screens.home.main.tours

import android.content.ClipData
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.util.classes.DataTypes
import com.natiqhaciyef.voyagers.view.viewmodel.tour.TourDetailsViewModel
import com.natiqhaciyef.voyagers.util.obj.FontList
import com.natiqhaciyef.voyagers.view.components.RatingBar
import com.natiqhaciyef.voyagers.view.navigation.NavigationData
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.*


@Preview
@Composable
fun TourDetailsScreen(
    data: Any = Any(),
    viewModel: TourDetailsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
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
            TourDetailsTopView(item, data, navController, viewModel)
            TourDetailsMainView(data, navController)
        }
    }
}


@Composable
fun TourDetailsTopView(
    infoType: DataTypes = DataTypes.PlaceModel,
    data: Any = Any(),
    navController: NavController,
    viewModel: TourDetailsViewModel,
) {
    val colorMatrix = floatArrayOf(
        0.8f, 0f, 0f, 0f, 0f,
        0f, 0.85f, 0f, 0f, 0f,
        0f, 0f, 0.9f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    val isLiked: MutableState<Boolean> = remember { mutableStateOf(false) }

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
            if (infoType == DataTypes.TourModel) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainter(data = (data as TourModel).image[0]),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            } else if (infoType == DataTypes.CampModel) {
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
                if (!isLiked.value) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Like",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                isLiked.value = !isLiked.value
                                if (data is TourModel) {
                                    viewModel.saveTourModel(data)
                                } else if (data is CampModel) {
                                    viewModel.saveCampModel(data)
                                }
                            },
                        tint = AppWhiteLightPurple
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Like",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                isLiked.value = !isLiked.value
                                if (data is TourModel) {
                                    viewModel.deleteTourModel(data)
                                } else if (data is CampModel) {
                                    viewModel.deleteCampModel(data)
                                }
                            },
                        tint = Red
                    )
                }
                val context = LocalContext.current

                Spacer(modifier = Modifier.width(30.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardCommandKey,
                    contentDescription = "Share",
                    tint = AppWhiteLightPurple,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            shareData(data, context)
                        }
                )
            }
        }
    }
}

//@Preview
@Composable
fun TourDetailsMainView(
    data: Any = Any(),
    navController: NavController
) {
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

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = if (data is TourModel) "Qiymət : ${data.price.toInt()} AZN" else if (data is CampModel) "Qiymət : ${data.price.toInt()} AZN" else "",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .width(200.dp)
                .height(55.dp),
            onClick = {
                // go to cart and details screen
                if (data is TourModel)
                    NavigationData.tourModel = data
                else if (data is CampModel)
                    NavigationData.campModel = data
                navController.navigate(ScreenID.PersonalInformation.name)
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


fun shareData(data: Any, context: Context){
    if (data is TourModel) {
        val introLink = data.image[0].toUri()
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Share tour image")
            putExtra(Intent.EXTRA_TEXT, data.toString())
            if (introLink != null) {
                putExtra(Intent.EXTRA_STREAM, introLink)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                clipData =
                    ClipData.newUri(
                        context.contentResolver,
                        "Image",
                        introLink
                    )
            }
        }
        val chooserIntent =
            Intent.createChooser(shareIntent, "Share with...")
        context.startActivity(chooserIntent)
    } else if (data is CampModel) {
        val introLink = data.image.toUri()
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Share tour image")
            putExtra(Intent.EXTRA_TEXT, data.toString())
            if (introLink != null) {
                putExtra(Intent.EXTRA_STREAM, introLink)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                clipData =
                    ClipData.newUri(
                        context.contentResolver,
                        "Image",
                        introLink
                    )
            }
        }
        val chooserIntent =
            Intent.createChooser(shareIntent, "Share with...")
        context.startActivity(chooserIntent)
    }
}