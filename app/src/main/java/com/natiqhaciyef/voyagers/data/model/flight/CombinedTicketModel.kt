package com.natiqhaciyef.voyagers.data.model.flight

data class CombinedTicketModel(
    var depTicketModel: TicketModel,
    var retTicketModel: TicketModel
)