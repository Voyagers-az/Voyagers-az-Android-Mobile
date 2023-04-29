package com.natiqhaciyef.data.data.model.tour

import com.natiqhaciyef.voyagers.data.model.enums.RegionSide

data class PlaceModel(
    var id: Int,
    var name: String,
    var image: MutableList<String>,
    var scope: String,
    var rating: Double,
    var side: String = RegionSide.NotSelected.side,
)
