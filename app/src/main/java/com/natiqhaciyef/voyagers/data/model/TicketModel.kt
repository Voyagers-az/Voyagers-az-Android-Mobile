package com.natiqhaciyef.voyagers.data.model

data class TicketModel(
    var id: Int,
    var price: Double,
    var type: String,
    var info: String,
    var userInfo : UserModel,
    var departureDate: String,
    var arrivalDate: String,
    var from: String,               // country - city
    var to: String,                 // country - city
    var flightDate: String,
    var companyName: String,
    var transfer: FlightTransfer?,
)
