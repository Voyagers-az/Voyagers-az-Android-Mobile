package com.natiqhaciyef.voyagers.data.model

data class PaymentDataModel(
    var paymentType: String,
    var nameOnCard: String,
    var numberOnCard: String,
    var expirationDate: String,
    var cvvCode: Int,
    var userModel: UserModel
)