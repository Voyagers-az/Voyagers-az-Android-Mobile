package com.natiqhaciyef.voyagers.data.model

import com.natiqhaciyef.voyagers.data.model.enums.Luggage

data class TicketModel(
    var price: Double,
    var info: String,
    var departureDate: String,
    var arrivalDate: String,
    var fromCity: String,               // country - city
    var fromCountry: String,               // country - city
    var toCity: String,                 // country - city
    var toCountry: String,                 // country - city
    var flightTime: Double,
    var companyNames: MutableList<String>,
    var transfer: FlightTransfer?,
    var luggage: Luggage
)
