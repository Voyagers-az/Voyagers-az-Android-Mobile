package com.natiqhaciyef.voyagers.view.viewmodel.home_categories_viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.car.CarModel
import com.natiqhaciyef.voyagers.data.model.car.CarRentModel
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
//        insertCarModelToFirestore(
//            CarRentModel(
//                id = 2,
//                place = "Bakı",
//                car = CarModel(
//                    id = 0,
//                    name = "BMW",
//                    brand = "M5",
//                    image = "https://cdn3.riastatic.com/photosnew/auto/photo/bmw_m5__488945092fx.jpg",
//                    engine = 4.4,
//                    year = 2018,
//                    description = "BMW M5 ən yaxşı seçimlərdəndir."
//                ),
//                dailyPrice = 70.0,
//                priceType = "AZN",
//                time = "4 gün",
//                ownerInfo = "Sadiq Hacıyev"
//            )
//        )
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
                        val id: Int = doc["id"].toString().toInt()
                        val place: String = doc["place"].toString()
                        val car: CarModel =
                            CarModel.mapToCarModel(doc["car"] as Map<String, String>)
                        val dailyPrice: Double = doc["dailyPrice"].toString().toDouble()
                        val priceType: String = doc["priceType"].toString()
                        val time: String = doc["time"].toString()
                        val ownerInfo: String = doc["ownerInfo"].toString()

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