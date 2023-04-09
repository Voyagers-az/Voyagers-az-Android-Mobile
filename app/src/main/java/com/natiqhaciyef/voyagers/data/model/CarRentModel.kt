package com.natiqhaciyef.voyagers.data.model

data class CarRentModel(
    var id: Int,
    var place: String,
    var car: CarModel,
    var dailyPrice: Double,
    var time: String,
    var ownerInfo: String
)