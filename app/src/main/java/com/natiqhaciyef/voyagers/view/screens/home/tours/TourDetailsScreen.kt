package com.natiqhaciyef.voyagers.view.screens.home.tours

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.util.DataTypes
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.ui.theme.AppAquatic
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue

@Preview
@Composable
fun TourDetailsScreen(
    data: Any = Any()
) {
    Log.d("MYLOG","$data")
    val item = dataTypeCaster(data)
    Box(
        modifier = Modifier
            .fillMaxSize()
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
            TourDetailsTopView(item, data)
        }
    }
}


@Composable
fun TourDetailsTopView(
    type: DataTypes = DataTypes.PlaceModel,
    data: Any = Any()
) {
    val colorMatrix = floatArrayOf(
        0.8f, 0f, 0f, 0f, 0f,
        0f, 0.85f, 0f, 0f, 0f,
        0f, 0f, 0.9f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    Spacer(modifier = Modifier.height(30.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = 5.dp,
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
            }else if(type == DataTypes.CampModel){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberImagePainter(data = (data as CampModel).image),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                )
            }
        }
    }
}


fun dataTypeCaster(data: Any): DataTypes = when (data) {
    is TourModel -> {
        DataTypes.TourModel
    }
    is CampModel -> {
        DataTypes.CampModel
    }
    is PlaceModel -> {
        DataTypes.PlaceModel
    }
    else -> {
        DataTypes.NonSelected
    }
}