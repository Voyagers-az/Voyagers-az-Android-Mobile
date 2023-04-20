package com.natiqhaciyef.voyagers.data.model.tour

import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel

data class TourAppealModel(
    var tourModel: TourModel,
    var payment: PaymentDataModel,
    var status: String  // Appeal Status - mainStatus
)
