package com.natiqhaciyef.voyagers.data.model

data class TourModel(
    var id: String,
    var name: String,
    var image: MutableList<String>,
    var info: String,
    var country: String,
    var route: MutableMap<String,String>,
    var price: Double,
    var personCount: Int,
    var rating: Double,
    var scope: String,
    var location: String,
    var companyName: String,
    var date: MutableMap<String, String>,
    var region: String = "Qeyd olunmayıb"
)
