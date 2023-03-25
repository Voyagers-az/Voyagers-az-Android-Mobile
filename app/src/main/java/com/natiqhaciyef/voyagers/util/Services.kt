package com.natiqhaciyef.voyagers.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.TrendingFlat
import com.natiqhaciyef.voyagers.data.model.ServiceModel

object Services {
    val services = mutableListOf(
        ServiceModel(
            title = "Yerli turların təşkil olunması",
            description = "Azərbaycan daxilində tanıdğınız, gəzməli və möhtəşəm məkanlarıı tur şəklində təşkil edə bilərsiniz",
            price = null,
            image = Icons.Default.TrendingFlat
        ),
        ServiceModel(
            title = "Xarici turların təşkil olunması",
            description = "Xarici ğlkələrin gəzməli, tarixi və ya istirahət mərkəzlərinə turları təşkil edə bilərsiniz",
            price = null,
            image = Icons.Default.TrendingFlat
        ),
        ServiceModel(
            title = "Əməkdaşlıq",
            description = "Həmçinin Voyagers.az şirkətinə partnyorluq üçün müraciət edə bilər və bizə dəstək göstərə bilərsiniz.",
            price = null,
            image = Icons.Default.AddBox
        )
    )
}