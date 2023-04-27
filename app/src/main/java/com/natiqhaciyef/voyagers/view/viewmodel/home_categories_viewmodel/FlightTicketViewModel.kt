package com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.enums.Luggage
import com.natiqhaciyef.voyagers.data.model.flight.CombinedTicketModel
import com.natiqhaciyef.voyagers.data.model.flight.FlightTransfer
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel
import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FlightTicketViewModel @Inject constructor() : BaseViewModel() {
    val firestore = Firebase.firestore
    val isLoading = mutableStateOf(true)
    val ticketList = mutableStateOf<List<TicketModel>>(mutableListOf())
    val combinedTicketList = mutableStateOf<List<CombinedTicketModel>>(mutableListOf())

    init {
        getCombinedFlightTickets()
//        insertCombinedFlightTicket(
//            DefaultModelImplementations.combinedTicketModel
//        )
    }

    private fun getTicketsFromFirebase() {
        val list = mutableListOf<TicketModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("FlightTickets").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val price = doc["price"].toString().toDouble()
                        val info = doc["info"].toString()
                        val image = doc["image"].toString()
                        val departureDate = doc["departureDate"].toString()
                        val arrivalDate = doc["arrivalDate"].toString()
                        val fromCity = doc["fromCity"].toString()
                        val fromCountry = doc["fromCountry"].toString()
                        val toCity = doc["toCity"].toString()
                        val toCountry = doc["toCountry"].toString()
                        val flightTime = doc["flightTime"].toString().toDouble()
                        val companyNames = doc["companyNames"] as MutableList<String>
                        val transfer = doc["transfer"] as Map<String, String>
                        val luggage = doc["luggage"].toString()

                        val checkTransfer = if (transfer.containsKey("Empty"))
                            null
                        else
                            FlightTransfer.stringMapperToFlightTransfer(transfer)

                        val ticketModel = TicketModel(
                            price = price,
                            info = info,
                            image = image,
                            departureDate = departureDate,
                            arrivalDate = arrivalDate,
                            fromCity = fromCity,
                            fromCountry = fromCountry,
                            toCity = toCity,
                            toCountry = toCountry,
                            flightTime = flightTime,
                            companyNames = companyNames,
                            transfer = checkTransfer,
                            luggage = luggage
                        )
                        list.add(ticketModel)
                    }

                    ticketList.value = list
                }
            }
        }
    }

    private fun insertFlightTicketToFirestore(ticketModel: TicketModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val ticketMap = hashMapOf<String, Any>()
            ticketMap["price"] = ticketModel.price
            ticketMap["info"] = ticketModel.info
            ticketMap["image"] = ticketModel.image
            ticketMap["departureDate"] = ticketModel.departureDate
            ticketMap["arrivalDate"] = ticketModel.arrivalDate
            ticketMap["fromCity"] = ticketModel.fromCity
            ticketMap["fromCountry"] = ticketModel.fromCountry
            ticketMap["toCity"] = ticketModel.toCity
            ticketMap["toCountry"] = ticketModel.toCountry
            ticketMap["flightTime"] = ticketModel.flightTime
            ticketMap["companyNames"] = ticketModel.companyNames
            ticketMap["transfer"] =
                if (ticketModel.transfer != null) ticketModel.transfer!!.flightTicketToStringMapper() else mapOf(
                    "Empty" to "Empty"
                )
            ticketMap["luggage"] = ticketModel.luggage.lowercase()

            firestore.collection("FlightTickets").document(UUID.randomUUID().toString())
                .set(ticketMap)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }

    private fun insertCombinedFlightTicket(combinedTicketModel: CombinedTicketModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val ticketMap = hashMapOf<String, Any>()
            ticketMap["depTicketModel"] = combinedTicketModel.depTicketModel.ticketModelToStringMapper()
            ticketMap["retTicketModel"] = combinedTicketModel.retTicketModel.ticketModelToStringMapper()
            firestore.collection("FlightTickets").document(UUID.randomUUID().toString())
                .set(ticketMap)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }

    private fun getCombinedFlightTickets() {
        val list = mutableListOf<CombinedTicketModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("FlightTickets").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val depTicketModel = TicketModel.stringMapperToTicketModel(doc["depTicketModel"] as Map<String, Any>)
                        val retTicketModel = TicketModel.stringMapperToTicketModel(doc["retTicketModel"] as Map<String, Any>)

                        val combinedTicketModel = CombinedTicketModel(
                            depTicketModel = depTicketModel,
                            retTicketModel = retTicketModel
                        )
                        list.add(combinedTicketModel)
                    }
                    combinedTicketList.value = list

                }
            }
        }
    }
}