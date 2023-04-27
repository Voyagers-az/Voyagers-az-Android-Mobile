package com.natiqhaciyef.voyagers.data.model.flight

import com.natiqhaciyef.voyagers.data.model.UserModel

data class TicketInfoModel(
    var id: Int,
    var depTicketModel: TicketModel,
    var retTicketModel: TicketModel,
    var userInfo : UserModel,
)
