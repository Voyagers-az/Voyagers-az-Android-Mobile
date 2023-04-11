package com.natiqhaciyef.voyagers.data.model

data class CarRentModel(
    var id: Int,
    var place: String,
    var car: CarModel,
    var dailyPrice: Double,
    var priceType: String,
    var time: String,
    var ownerInfo: String
)