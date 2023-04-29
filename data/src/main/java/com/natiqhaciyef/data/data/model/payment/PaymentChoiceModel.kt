package com.natiqhaciyef.voyagers.data.model.payment

import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes

data class PaymentChoiceModel(
    var type: PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)