package com.natiqhaciyef.voyagers.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_model")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var email: String,
    var phone: String,
    var password: String
)
