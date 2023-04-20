package com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.flight.FlightTransfer
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel
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

    init {
        getTicketsFromFirebase()
//        insertFlightTicketToFirestore(DefaultModelImplementations.ticketModel)
    }

    private fun getTicketsFromFirebase(){
        val list = mutableListOf<TicketModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("FlightTickets").addSnapshotListener{ value, error ->
                if (value != null && !value.isEmpty){
                    val docs = value.documents
                    list.clear()
                    for (doc in docs){
                        val price = doc["price"].toString().toDouble()
                        val info = doc["info"].toString()
                        val departureDate = doc["departureDate"].toString()
                        val arrivalDate = doc["arrivalDate"].toString()
                        val fromCity = doc["fromCity"].toString()
                        val fromCountry = doc["fromCountry"].toString()
                        val toCity = doc["toCity"].toString()
                        val toCountry = doc["toCountry"].toString()
                        val flightTime = doc["flightTime"].toString().toDouble()
                        val companyNames = doc["companyNames"] as MutableList<String>
                        val transfer = FlightTransfer.stringMapperToFlightTransfer(doc["transfer"] as Map<String, String>)
                        val luggage = doc["luggage"].toString()

                        val ticketModel = TicketModel(
                            price = price,
                            info = info,
                            departureDate = departureDate,
                            arrivalDate = arrivalDate,
                            fromCity = fromCity,
                            fromCountry = fromCountry,
                            toCity = toCity,
                            toCountry = toCountry,
                            flightTime = flightTime,
                            companyNames = companyNames,
                            transfer = transfer,
                            luggage = luggage
                        )
                        list.add(ticketModel)
                    }

                    ticketList.value = list
                }
            }
        }
    }

    private fun insertFlightTicketToFirestore(ticketModel: TicketModel){
        viewModelScope.launch(Dispatchers.IO) {
            val ticketMap = hashMapOf<String, Any>()
            ticketMap["price"] = ticketModel.price
            ticketMap["info"] = ticketModel.info
            ticketMap["departureDate"] = ticketModel.departureDate
            ticketMap["arrivalDate"] = ticketModel.arrivalDate
            ticketMap["fromCity"] = ticketModel.fromCity
            ticketMap["fromCountry"] = ticketModel.fromCountry
            ticketMap["toCity"] = ticketModel.toCity
            ticketMap["toCountry"] = ticketModel.toCountry
            ticketMap["flightTime"] = ticketModel.flightTime
            ticketMap["companyNames"] = ticketModel.companyNames
            ticketMap["transfer"] = if (ticketModel.transfer != null) ticketModel.transfer!!.flightTicketToStringMapper() else mapOf("" to "")
            ticketMap["luggage"] = ticketModel.luggage

            firestore.collection("FlightTickets").document(UUID.randomUUID().toString())
                .set(ticketMap)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }
}