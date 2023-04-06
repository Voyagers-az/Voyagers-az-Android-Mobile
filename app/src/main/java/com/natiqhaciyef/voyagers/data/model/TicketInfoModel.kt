package com.natiqhaciyef.voyagers.data.model

import com.natiqhaciyef.voyagers.data.model.enums.Luggage

data class TicketInfoModel(
    var id: Int,
    var ticketModel: TicketModel,
    var userInfo : UserModel,
)
