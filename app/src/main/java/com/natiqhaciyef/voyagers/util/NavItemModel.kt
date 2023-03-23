package com.natiqhaciyef.voyagers.util

import androidx.compose.ui.graphics.vector.ImageVector
import javax.annotation.concurrent.Immutable

data class NavItemModel(
    val image: Int,
    val title: String
)


@Immutable
data class NavItem(val icon: ImageVector, val title: String)