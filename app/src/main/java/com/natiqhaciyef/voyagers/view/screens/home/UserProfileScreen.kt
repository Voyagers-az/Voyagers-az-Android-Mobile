package com.natiqhaciyef.voyagers.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.voyagers.view.ui.theme.AppGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppLightGray
import com.natiqhaciyef.voyagers.view.ui.theme.AppWhiteLightPurple
import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.view.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel

@Preview
@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppWhiteLightPurple)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        UserProfileTopView()
        UserProfileMainView()
        Spacer(modifier = Modifier.height(65.dp))
    }
}

//@Preview
@Composable
fun UserProfileTopView() {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "User profile icon",
        modifier = Modifier
            .size(200.dp)
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
            .background(AppWhiteLightPurple),
    )
}

//@Preview
@Composable
fun UserProfileMainView(
    registerViewModel: RegistrationViewModel = hiltViewModel()
) {
    Spacer(modifier = Modifier.height(30.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppLightGray)
    ) {
        Text(
            text = "Kontentlər",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Start
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
        ) {
            LikedPosts()
            LastTours()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppLightGray)
    ) {
        Text(
            text = "Parametrlər",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 7.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Start
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
        ) {
            ResetPasswordUP()
            ChangeUserName()
            CardInfo()
            LanguageSupport()
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier
            .width(250.dp)
            .height(55.dp),
        onClick = {
             registerViewModel.auth.signOut()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppDarkBlue
        ),
        shape = RoundedCornerShape(10.dp)
    ) {

        Text(
            text = "Çıxış etmək",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = AppWhiteLightPurple
        )
    }

    Spacer(modifier = Modifier.height(5.dp))

}


@Composable
private fun LikedPosts() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Liked posts",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Bəyənilən turlar",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun LastTours() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Bookmarks,
                contentDescription = "Last tours",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Son gəzintilər",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ResetPasswordUP() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LockReset,
                contentDescription = "Password change",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Şifrəni yeniləmək",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun LanguageSupport() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Language,
                contentDescription = "Language support",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Dil dəyişmək",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun ChangeUserName() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ManageAccounts,
                contentDescription = "Username modification",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "İstifadəçi adını dəyişmək",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CardInfo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = "Card info",
                modifier = Modifier
                    .size(30.dp),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Kart məlumatlarını yeniləmək",
                fontSize = 20.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}
