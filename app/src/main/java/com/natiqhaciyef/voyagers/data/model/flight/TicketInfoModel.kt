package com.natiqhaciyef.voyagers.data.model.flight

import com.natiqhaciyef.voyagers.data.model.UserModel

data class TicketInfoModel(
    var id: Int,
    var ticketModel: TicketModel,
    var userInfo : UserModel,
)
