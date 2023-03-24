package com.natiqhaciyef.voyagers.data.model

data class CarRentModel(
    var id: Int,
    var name: String,
    var car: CarModel,
    var price: Double,
    var time: String,
    var ownerName: String
)