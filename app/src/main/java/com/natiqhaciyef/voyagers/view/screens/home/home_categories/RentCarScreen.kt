package com.natiqhaciyef.voyagers.view.screens.home.home_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.voyagers.view.ui.theme.*

@Preview
@Composable
fun RentCarScreen() {
    val search = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(AppAquatic)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            RentCarTopView(search)
        }
    }
}


@Preview
@Composable
private fun RentCarTopView(
    search: MutableState<String> = mutableStateOf("")
) {
    var expand = remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(7.dp),
        value = search.value,
        onValueChange = {
            search.value = it
        },
        textStyle = TextStyle.Default.copy(
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        ),
        enabled = true,
        readOnly = false,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = if (expand.value)
                    Icons.Default.ExpandMore
                else
                    Icons.Default.ExpandLess,
                contentDescription = "Enlarge"
            )
        },
        label = {
            Text(
                text = "Markanı daxil edin",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            trailingIconColor = AppDarkBlue,
            cursorColor = AppDarkBlue,

            focusedBorderColor = AppDarkBlue,
            unfocusedBorderColor = Color.Black,
            focusedLabelColor = AppDarkBlue,
            unfocusedLabelColor = AppGray,
            leadingIconColor = AppDarkBlue,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
    )
}