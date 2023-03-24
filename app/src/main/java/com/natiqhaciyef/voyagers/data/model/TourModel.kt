package com.natiqhaciyef.voyagers.data.model

data class TourModel(
    var id: Int,
    var name: String,
    var info: String,
    var route: MutableList<String>,
    var price: Double,
    var personCount: Int,
    var rating: Double
)
