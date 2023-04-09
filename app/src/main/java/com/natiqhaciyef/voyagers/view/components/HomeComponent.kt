package com.natiqhaciyef.voyagers.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natiqhaciyef.voyagers.util.ContactList
import com.natiqhaciyef.voyagers.util.classes.ContactModel
import com.natiqhaciyef.voyagers.view.navigation.ScreenID
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue

//@Preview
@Composable
fun CategoryCardView(
    icon: ImageVector = Icons.Default.DirectionsCar,
    navController: NavController
) {
    Card(
        modifier = Modifier.size(70.dp)
            .clickable {
                navigateChecker(icon, navController)
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = AppDarkBlue,
        contentColor = Color.White,
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


//@Preview
@Composable
fun ContactCardItem(contactModel: ContactModel = ContactList.list[0]) {
    Card(
        modifier = Modifier
            .size(85.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = contactModel.icon,
                contentDescription = "Contact icon",
                modifier = Modifier.size(35.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = contactModel.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

fun navigateChecker(icon: ImageVector, navController: NavController) = when(icon){
    Icons.Default.DirectionsCar -> { navController.navigate(ScreenID.RentCar.name) }
    Icons.Default.Train, -> { navController.navigate(ScreenID.TrainTour.name) }
    Icons.Default.Flight -> { navController.navigate(ScreenID.FlightTickets.name) }
    else -> navController.navigate(ScreenID.MainScreenLine.name)
}