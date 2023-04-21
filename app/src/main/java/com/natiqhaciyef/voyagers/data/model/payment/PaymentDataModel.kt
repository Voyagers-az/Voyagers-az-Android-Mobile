package com.natiqhaciyef.voyagers.data.model.payment

import com.natiqhaciyef.voyagers.data.model.UserModel


data class PaymentDataModel(
    val id: Int,
    var paymentType: String,
    var nameOnCard: String,
    var numberOnCard: String,
    var expirationDate: String,
    var cvvCode: Int,
    var userModel: UserModel
)