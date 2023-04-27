package com.natiqhaciyef.voyagers.view.navigation

import com.natiqhaciyef.voyagers.data.model.flight.TicketInfoModel
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel

object FlightTicketData {
    var ticketInfoModel: TicketInfoModel? = null
    var depTicket: TicketModel? = null
    var retTickets: List<TicketModel?> = mutableListOf()
    var retTicket: TicketModel? = null
}