package com.natiqhaciyef.voyagers.view.components

//https://freebiefy.com/free-travel-app-ui-design/
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.util.NavItemModel
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.ServiceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.RegionSide
import com.natiqhaciyef.voyagers.data.model.enums.TourScope
import com.natiqhaciyef.voyagers.util.Services
import com.natiqhaciyef.voyagers.view.ui.theme.*
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun BottomShadow(
    alpha: Float = 0.1f, height: Dp = 8.dp,
    padding: Dp = 0.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = padding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Black.copy(alpha = alpha),
                        Color.Transparent,
                    )
                )
            )
    )
}

@Composable
fun BottomShadow(modifier: Modifier) {
    Box(
        modifier = modifier
    )
}

@Composable
fun NavBar(
    selectedIndex: MutableState<Int>,
    list: MutableList<NavItemModel> = mutableListOf(
        NavItemModel(image = R.drawable.home_icon, title = "Ana səhifə"),
        NavItemModel(image = R.drawable.tour_icon, title = "Turlar"),
        NavItemModel(image = R.drawable.account_circle_icon, title = "Profil")
    )
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(AppDarkBlue, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            list.forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(11f)
                        .clickable { selectedIndex.value = index },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = if (selectedIndex.value == index)
                            Modifier.offset(y = (-8).dp)
                        else
                            Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selectedIndex.value == index) AppYellow
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .size(45.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = icon.image),
                                contentDescription = "content",
                                modifier = Modifier.size(25.dp),
                                tint = if (selectedIndex.value == index) White else Gray
                            )
                        }
                        AnimatedVisibility(visible = (selectedIndex.value == index)) {
                            Text(
                                text = icon.title,
                                modifier = Modifier,
                                color = White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}


//@Preview
@ExperimentalPagerApi
@Composable
fun CustomViewPager(list: MutableList<TourModel> = mutableListOf()) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = list.size
    )

    LaunchedEffect(key1 = Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 40.dp)
    ) { page ->

        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 30.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            val item = list[page]
            ViewPagerItem(item)
        }
    }
}

@Composable
fun ViewPagerItem(item: TourModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppDarkBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(data = item.image[0]),
                contentDescription = "Places",
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppAquaticLight,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = item.info,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

//@Preview
@Composable
fun CurvedRectangle(
    height: Int = 245,
    curveHeight: Int = 145,
    color: Color = AppAquatic
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    ) {
        val path = Path().apply {
            lineTo(0f, size.height - curveHeight)
            cubicTo(
                0f,
                size.height - curveHeight / 2,
                size.width / 2,
                size.height,
                size.width,
                size.height - curveHeight / 2
            )
            lineTo(size.width, 0f)
            close()
        }
        drawPath(
            path = path,
            color = color
        )
    }
}


//@Preview
@Composable
fun CategoryCardView(
    icon: ImageVector = Icons.Default.DirectionsCar
) {
    Card(
        modifier = Modifier.size(70.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = AppDarkBlue,
        contentColor = White,
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Category",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Preview
@Composable
fun PlaceItem(
    item: PlaceModel = PlaceModel(
        id = 0,
        name = "ViaTour",
        image = "",
        scope = TourScope.Global.scope,
        side = RegionSide.North.side,
        rating = 4.5
    ),
    isLoading: MutableState<Boolean> = mutableStateOf(true)
) {

    val colorMatrix = floatArrayOf(
        0.5f, 0f, 0f, 0f, 0f,
        0f, 0.5f, 0f, 0f, 0f,
        0f, 0f, 0.5f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(330.dp)
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = White,
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
            painter = rememberImagePainter(data = item.image),
            contentDescription = "Place image",
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            modifier = Modifier
                .fillMaxSize(),
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
                text = "${item.name} turu",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(5.dp))

            RatingBar(rating = item.rating)

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Preview
@Composable
fun ServiceCardItem(
    serviceModel: ServiceModel = Services.services[0]
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppDarkBlue),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    text = serviceModel.title,
                    color = White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 20.dp)
                        .align(Alignment.CenterStart)
                )

                Icon(
                    imageVector = serviceModel.image!!,
                    contentDescription = "Service icon",
                    tint = White,
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}

