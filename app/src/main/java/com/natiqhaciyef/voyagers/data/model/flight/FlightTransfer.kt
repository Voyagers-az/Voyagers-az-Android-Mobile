package com.natiqhaciyef.voyagers.data.model.flight

data class FlightTransfer(
    var id: Int,
    var arrivalDate: String,
    var departureDate: String,
    var landedPlace:String,
    var waitingTime: Double,
    var details: String = ""
){
    fun flightTicketToStringMapper(): Map<String, String>{
        return mapOf(
            "id" to "$id",
            "arrivalDate" to "$arrivalDate",
            "departureDate" to "$departureDate",
            "landedPlace" to "$landedPlace",
            "waitingTime" to "$waitingTime",
            "details" to "$details",
        )
    }

    companion object{
        fun stringMapperToFlightTransfer(map: Map<String, String>): FlightTransfer {
            return FlightTransfer(
                id = map["id"]!!.toInt(),
                arrivalDate = map["arrivalDate"]!!,
                departureDate = map["departureDate"]!!,
                landedPlace = map["landedPlace"]!!,
                waitingTime = map["waitingTime"]!!.toDouble(),
                details = map["details"]!!
            )
        }
    }

}
