package com.natiqhaciyef.voyagers.data.model

import com.natiqhaciyef.voyagers.data.model.enums.Luggage

data class TicketModel(
    var id: Int,
    var price: Double,
    var info: String,
    var userInfo : UserModel,
    var departureDate: String,
    var arrivalDate: String,
    var from: String,               // country - city
    var to: String,                 // country - city
    var flightDate: Double,
    var companyNames: MutableList<String>,
    var transfer: FlightTransfer?,
    var luggage: Luggage
)
