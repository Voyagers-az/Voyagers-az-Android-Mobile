package com.natiqhaciyef.voyagers.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector


data class ServiceModel(
    var title: String,
    var description: String,
    var image: ImageVector?,
    var price: Double?,
)