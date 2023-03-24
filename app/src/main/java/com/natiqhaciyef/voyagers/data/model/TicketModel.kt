package com.natiqhaciyef.voyagers.data.model

data class TicketModel(
    var id: Int,
    var price: Double,
    var type: String,
    var info: String,
    var userCount: Int
)
