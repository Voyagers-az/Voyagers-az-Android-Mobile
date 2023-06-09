package com.natiqhaciyef.voyagers.view.screens.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.natiqhaciyef.voyagers.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.data.data.model.tour.PlaceModel
import com.natiqhaciyef.voyagers.util.obj.CategoryIcons
import com.natiqhaciyef.voyagers.util.obj.ContactList
import com.natiqhaciyef.voyagers.view.components.*
import com.natiqhaciyef.voyagers.view.ui.theme.*
import com.natiqhaciyef.voyagers.view.viewmodel.HomeViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel

//@Preview
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val list = CategoryIcons.list
    val places = viewModel.placesList
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
                HomeTopView(navController, list)
                HomeMainPartView(places, viewModel.isLoading, navController)
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

//@Preview
@Composable
fun HomeTopView(
    navController: NavController,
    list: MutableList<ImageVector> = mutableListOf(),
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val users = remember { viewModel.fums }
    val user = users.value.filter {
        it.email == FirebaseAuth.getInstance().currentUser!!.email!!
    }

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
                    append(text = stringResource(id = R.string.welcome))
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
                    append(
                        text = if (user.isNotEmpty())
                            " ${user[0].username}"
                        else
                            "No name"
                    )
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
                    append(text = stringResource(id = R.string.welcome_info))
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
                CategoryCardView(icon = item, navController = navController)
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
    val context = LocalContext.current
    Column {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.places),
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


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.contact),
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
            ContactCardItem(
                contactModel = ContactList.list[0],
                navController = navController
            )
            Spacer(modifier = Modifier.width(30.dp))
            ContactCardItem(
                contactModel = ContactList.list[1],
                navController = navController
            )
            Spacer(modifier = Modifier.width(30.dp))
            ContactCardItem(
                contactModel = ContactList.list[2],
                navController = navController
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
