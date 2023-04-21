package com.natiqhaciyef.voyagers.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tour_model")
data class TourModelDatabase (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var image: String,
    var info: String,
    var country: String,
    var route: String,
    var price: Double,
    var personCount: Int,
    var rating: Double,
    var scope: String,
    var location: String,
    var companyName: String,
    var date: String,
    var region: String = "Qeyd olunmayıb",
    var isLiked: Boolean = false
)