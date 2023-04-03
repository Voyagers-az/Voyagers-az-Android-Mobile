package com.natiqhaciyef.voyagers.data.model

data class FlightTransfer(
    var id: Int,
    var arrivalDate: String,
    var departureDate: String,
    var landedPlace:String,
    var watingTime: Double,
    var details: String = ""
)
