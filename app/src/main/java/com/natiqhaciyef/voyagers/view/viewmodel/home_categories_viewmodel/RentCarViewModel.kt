package com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.CarModel
import com.natiqhaciyef.voyagers.data.model.CarRentModel
import com.natiqhaciyef.voyagers.util.DefaultModelImplementations
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentCarViewModel @Inject constructor() : BaseViewModel() {
    val firestore = Firebase.firestore
    val isLoading = mutableStateOf(true)
    val carsList = mutableStateOf<List<CarRentModel>>(mutableListOf())

    init {
        getCarsFromFirebase()
//        insertCarModelToFirestore(DefaultModelImplementations.carRentModel)
    }

    private fun getCarsFromFirebase() {
        val list = mutableListOf<CarRentModel>()
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Cars").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()

                    for (doc in docs) {
                        var id: Int = doc["id"].toString().toInt()
                        var place: String = doc["place"].toString()
                        var car: CarModel =
                            CarModel.mapToCarModel(doc["car"] as Map<String, String>)
                        var dailyPrice: Double = doc["dailyPrice"].toString().toDouble()
                        var priceType: String = doc["priceType"].toString()
                        var time: String = doc["time"].toString()
                        var ownerInfo: String = doc["ownerInfo"].toString()

                        val carRentModel = CarRentModel(
                            id = id,
                            place = place,
                            car = car,
                            dailyPrice = dailyPrice,
                            priceType = priceType,
                            time = time,
                            ownerInfo = ownerInfo
                        )

                        list.add(carRentModel)
                    }

                    carsList.value = list
                }
            }
        }
    }


    private fun insertCarModelToFirestore(carModel: CarRentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val carMap = hashMapOf<String, Any>()
            carMap["id"] = carModel.id
            carMap["place"] = carModel.place
            carMap["car"] = carModel.car.carModelToMap()
            carMap["dailyPrice"] = carModel.dailyPrice
            carMap["priceType"] = carModel.priceType
            carMap["time"] = carModel.time
            carMap["ownerInfo"] = carModel.ownerInfo

            firestore.collection("Cars").document(carModel.ownerInfo)
                .set(carMap)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }

}