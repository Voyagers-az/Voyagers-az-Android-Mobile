package com.natiqhaciyef.voyagers.view.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.RegionSide
import com.natiqhaciyef.voyagers.data.model.enums.TourScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TourViewModel @Inject constructor(

) : BaseViewModel() {
    val firestore = Firebase.firestore
    val toursList = mutableStateOf<List<TourModel>>(mutableListOf())
    val campList = mutableStateOf<List<CampModel>>(mutableListOf())
    val isLoading = mutableStateOf(true)

    init {
        getToursFromFirebase()
        getCampsFromFirebase()
//        sendToursToFirebase()
//        sendCampsToFirebase()
    }

    private fun getToursFromFirebase() {
        val list = mutableListOf<TourModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Tours").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()

                    for (doc in docs) {
                        val id = doc["id"].toString()
                        var name = doc["name"] as String
                        var image = doc["image"] as MutableList<String>
                        var info = doc["info"] as String
                        var country = doc["country"] as String
                        var route = doc["route"] as MutableMap<String, String>
                        var price = doc["price"].toString().toDouble()
                        var personCount = doc["personCount"].toString().toInt()
                        var rating = doc["rating"] as Double
                        var scope = doc["scope"] as String
                        var location = doc["location"] as String
                        var companyName = doc["companyName"] as String
                        var region = doc["region"] as String

                        val tourModel = TourModel(
                            id = id,
                            name = name,
                            image = image,
                            info = info,
                            country = country,
                            route = route,
                            price = price,
                            personCount = personCount,
                            rating = rating,
                            scope = scope,
                            companyName = companyName,
                            region = region,
                            location = location
                        )

                        list.add(tourModel)
                    }
                    isLoading.value = false
                    toursList.value = list
                }
            }
        }
    }

    private fun sendToursToFirebase(tourModel: TourModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val tourMap = hashMapOf<String, Any>()
            tourMap["id"] = tourModel.id
            tourMap["name"] = tourModel.name
            tourMap["image"] = tourModel.image
            tourMap["info"] = tourModel.info
            tourMap["country"] = tourModel.country
            tourMap["route"] = tourModel.route
            tourMap["price"] = tourModel.price
            tourMap["personCount"] = tourModel.personCount
            tourMap["rating"] = tourModel.rating
            tourMap["scope"] = tourModel.scope
            tourMap["location"] = tourModel.location
            tourMap["companyName"] = tourModel.companyName
            tourMap["region"] = tourModel.region

            firestore.collection("Tours")
                .document("${tourModel.name} - ${tourModel.companyName}")
                .set(tourMap)
                .addOnSuccessListener {

                }.addOnFailureListener {
                    Log.d("MYLOG", "${it.message} -> Error coused")
                }
        }
    }

    private fun getCampsFromFirebase() {
        val list = mutableListOf<CampModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Camps").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()

                    for (doc in docs) {
                        var id = doc["id"].toString().toInt()
                        var name = doc["name"] as String
                        var info = doc["info"] as String
                        var image = doc["image"] as String
                        var location = doc["location"] as String
                        var scope = doc["scope"] as String
                        var country = doc["country"] as String
                        var rating = doc["rating"].toString().toDouble()
                        var companyName = doc["companyName"] as String
                        var price = doc["price"].toString().toDouble()
                        var personCount = doc["personCount"].toString().toInt()
                        var region = doc["region"] as String
                        var date = doc["date"] as MutableMap<String, String>

                        val campModel = CampModel(
                            id = id,
                            name = name,
                            info = info,
                            image = image,
                            location = location,
                            scope = scope,
                            country = country,
                            companyName = companyName,
                            price = price,
                            personCount = personCount,
                            region = region,
                            date = date,
                            rating = rating
                        )

                        Log.d("MYLOG", "${campModel}")
                        list.add(campModel)
                    }

                    campList.value = list
                }
            }
        }
    }

    private fun sendCampsToFirebase(campModel: CampModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val tourMap = hashMapOf<String, Any>()
            tourMap["id"] = campModel.id
            tourMap["name"] = campModel.name
            tourMap["location"] = campModel.location
            tourMap["image"] = campModel.image
            tourMap["info"] = campModel.info
            tourMap["country"] = campModel.country
            tourMap["date"] = campModel.date
            tourMap["price"] = campModel.price
            tourMap["personCount"] = campModel.personCount
            tourMap["rating"] = campModel.rating
            tourMap["scope"] = campModel.scope
            tourMap["companyName"] = campModel.companyName
            tourMap["region"] = campModel.region

            firestore.collection("Camps").document("${campModel.name} - ${campModel.companyName}")
                .set(tourMap)
                .addOnSuccessListener {

                }.addOnFailureListener {
                    Log.d("MYLOG", "${it.message} -> Error coused")
                }
        }
    }
}