package com.natiqhaciyef.voyagers.util

import com.natiqhaciyef.voyagers.R
import com.natiqhaciyef.voyagers.data.model.PaymentChoiceModel
import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes

object PaymentMethodList {
    val list = mutableListOf(
        PaymentChoiceModel(
            type = PaymentTypes.VISA,
            image = R.drawable.visa,
            isSelected = true
        ),
        PaymentChoiceModel(
            type = PaymentTypes.MASTERCARD,
            image = R.drawable.mastercard,
            isSelected = false
        ),
        PaymentChoiceModel(
            type = PaymentTypes.PAYPAL,
            image = R.drawable.paypal,
            isSelected = false
        ),
    )
}