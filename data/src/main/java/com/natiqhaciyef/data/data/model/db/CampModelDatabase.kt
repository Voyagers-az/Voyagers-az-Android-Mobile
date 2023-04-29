package com.natiqhaciyef.voyagers.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "camp_model")
data class CampModelDatabase(
    @PrimaryKey(autoGenerate = true)
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
    var date: String,
    var isLiked: Boolean
)
