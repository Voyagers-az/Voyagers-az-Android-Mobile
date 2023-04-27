package com.natiqhaciyef.voyagers.util.obj

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.natiqhaciyef.voyagers.R

object FontList {
    val fontFamily = FontFamily(
        fonts = listOf(
            Font(resId = R.font.ubuntlu_light),
            Font(resId = R.font.ubuntu_light_italic),
            Font(resId = R.font.ubuntu_bold),
            Font(resId = R.font.ubuntu_bold_italic),
            Font(resId = R.font.ubuntu_medium),
            Font(resId = R.font.ubuntu_medium_italic),
            Font(resId = R.font.ubuntu_regular),
            Font(resId = R.font.ubuntu_italic),
        )
    )

    val fontFamily2 = FontFamily(
        fonts = listOf(
            Font(resId = R.font.rubik_bold)
        )
    )
}