package com.natiqhaciyef.voyagers.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CampModel(
    var id: Int,
    var name: String,
    var info: String,
    var image: String,
    var location: String,
    var scope: String,
    var country: String,
    var companyName: String,
    var price: Double,
    var personCount: Int,
    var region: String,
    var rating: Double,
    var date: MutableMap<String, String>,
    var isLiked: Boolean
)
