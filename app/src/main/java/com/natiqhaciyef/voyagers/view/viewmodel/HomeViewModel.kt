package com.natiqhaciyef.voyagers.view.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    val firestore = Firebase.firestore
    val placesList = mutableStateOf<List<PlaceModel>>(mutableListOf())
    val isLoading = mutableStateOf(true)

    init {
        getPlaces()
//        sendPlacesToFirebase(Tours.list[1])
    }

    private fun getPlaces(){
        val list = mutableListOf<PlaceModel>()
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Places").addSnapshotListener{ value, error ->
                if (value != null && !value.isEmpty){
                    val docs = value.documents
                    list.clear()
                    for (doc in docs){
                        val id = doc.get("id").toString().toInt()
                        val name = doc.get("name") as String
                        val image = doc.get("image") as MutableList<String>
                        val scope = doc.get("scope") as String
                        val side = doc.get("side") as String
                        val rating = doc.get("rating") as Double

                        val placeModel = PlaceModel(
                            id = id,
                            name = name,
                            image = image,
                            scope = scope,
                            side = side,
                            rating = rating
                        )
                        list.add(placeModel)
                    }
//                    isLoading.value = false
                    placesList.value = list
                }
            }
        }
    }


    private fun sendPlacesToFirebase(placeModel: PlaceModel){
        viewModelScope.launch(Dispatchers.IO) {
            val placeMap = hashMapOf<String, Any>()
            placeMap["id"] = placeModel.id
            placeMap["name"] = placeModel.name
            placeMap["image"] = placeModel.image
            placeMap["rating"] = placeModel.rating
            placeMap["scope"] = placeModel.scope
            placeMap["side"] = placeModel.side

            firestore.collection("Places").document(placeModel.name)
                .set(placeMap)
                .addOnSuccessListener {

                }.addOnFailureListener {
                    Log.d("MYLOG","${it.message} -> Error coused")
                }
        }
    }
}