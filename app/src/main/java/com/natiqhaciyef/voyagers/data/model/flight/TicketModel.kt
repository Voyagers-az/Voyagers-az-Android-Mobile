package com.natiqhaciyef.voyagers.data.model.flight

data class TicketModel(
    var price: Double,
    var info: String,
    var image: String,
    var departureDate: String,
    var arrivalDate: String,
    var fromCity: String,               // country - city
    var fromCountry: String,               // country - city
    var toCity: String,                 // country - city
    var toCountry: String,                 // country - city
    var flightTime: Double,
    var companyNames: MutableList<String>,
    var transfer: FlightTransfer?,
    var luggage: String
) {

    fun ticketModelToStringMapper(): Map<String, Any?> {
        return mapOf(
            "price" to "$price",
            "info" to "$info",
            "image" to "$image",
            "departureDate" to "$departureDate",
            "arrivalDate" to "$arrivalDate",
            "fromCity" to "$fromCity",
            "fromCountry" to "$fromCountry",
            "toCity" to "$toCity",
            "toCountry" to "$toCountry",
            "flightTime" to "$flightTime",
            "companyNames" to companyNames,
            "transfer" to transfer?.flightTicketToStringMapper(),
            "luggage" to "$luggage"
        )
    }


    fun stringMapperToTicketModel(map: Map<String, Any>): TicketModel {
        return TicketModel(
            price = map["price"]!!.toString().toDouble(),
            info = map["info"]!!.toString(),
            image = map["image"]!!.toString(),
            departureDate = map["departureDate"]!!.toString(),
            arrivalDate = map["arrivalDate"]!!.toString(),
            fromCity = map["fromCity"]!!.toString(),
            fromCountry = map["fromCountry"]!!.toString(),
            toCity = map["toCity"]!!.toString(),
            toCountry = map["toCountry"]!!.toString(),
            flightTime = map["flightTime"]!!.toString().toDouble(),
            companyNames = map["companyNames"]!! as MutableList<String>,
            transfer = FlightTransfer.stringMapperToFlightTransfer(map["transfer"]!! as Map<String, String>),
            luggage = map["luggage"].toString()
        )
    }


}
