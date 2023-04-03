package com.natiqhaciyef.voyagers.data.model

data class FlightTransfer(
    var id: Int,
    var landedCountry:String,
    var stopTime: String,
    var details: String = ""
)
