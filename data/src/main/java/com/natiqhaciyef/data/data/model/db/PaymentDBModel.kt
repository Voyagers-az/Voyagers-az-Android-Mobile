package com.natiqhaciyef.voyagers.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.natiqhaciyef.voyagers.data.model.UserModel

@Entity(tableName = "payment_table")
data class PaymentDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var paymentType: String,
    var nameOnCard: String,
    var numberOnCard: String,
    var expirationDate: String,
    var cvvCode: Int,
    var userModel: String       // String to Map, Map to UserModel
)