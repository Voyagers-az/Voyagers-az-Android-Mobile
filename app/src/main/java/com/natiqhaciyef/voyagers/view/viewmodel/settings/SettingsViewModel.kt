package com.natiqhaciyef.voyagers.view.viewmodel.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.local.repository.LocalRepository
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import com.natiqhaciyef.voyagers.data.model.tour.TourAppealModel
import com.natiqhaciyef.voyagers.data.model.tour.TourModel
import com.natiqhaciyef.voyagers.util.functions.toMapForFirebase
import com.natiqhaciyef.voyagers.util.functions.toPaymentDataModel
import com.natiqhaciyef.voyagers.util.functions.toTourModel
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import com.natiqhaciyef.voyagers.view.viewmodel.RegistrationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : BaseViewModel() {
    val firestore = Firebase.firestore
    val fumList = mutableStateOf<MutableList<FirebaseUserModel>>(mutableListOf())
    val appeals = mutableStateOf<MutableList<TourAppealModel>>(mutableListOf())

    init {
        getUsernameFromFirebase()
        getTourApplicationStatusFromFirebase()
    }

    fun getUsernameFromFirebase() {
        val list = mutableListOf<FirebaseUserModel>()
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Users").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val username = doc["username"].toString()
                        val email = doc["email"].toString()
                        val phone = doc["phone"].toString()

                        val fum = FirebaseUserModel(username, email, phone)
                        list.add(fum)
                    }

                    fumList.value = list
                }
            }
        }
    }

    fun sendUserDataToFirebase(fum: FirebaseUserModel){
        viewModelScope.launch(Dispatchers.IO) {
            val fumMap = hashMapOf<String, String>()
            fumMap["email"] = fum.email
            fumMap["phone"] = fum.phone
            fumMap["username"] = fum.username

            firestore.collection("Users")
                .document(fum.email)
                .set(fumMap)
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
        }
    }

    fun deleteFumFromFirestore(fum:FirebaseUserModel){
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Users")
                .document(fum.email)
                .delete()
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
        }
    }

    fun filter(userName: String): MutableList<FirebaseUserModel> {
        return if (fumList.value.isNotEmpty()) {
            fumList.value.filter {
                it.username == userName
            }.toMutableList()
        } else {
            mutableListOf()
        }
    }

    fun sendTourAppealStatusToFirebase(tourAppealModel: TourAppealModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val tourAppealMap = hashMapOf<String, Any>()
            tourAppealMap["tourModel"] = tourAppealModel.tourModel.toMapForFirebase()
            tourAppealMap["payment"] = tourAppealModel.payment.toMapForFirebase()
            tourAppealMap["status"] = tourAppealModel.status

            firestore.collection("Application")
                .document("${tourAppealModel.payment.userModel.name} - ${tourAppealModel.payment.userModel.email}")
                .set(tourAppealMap)
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
        }
    }

    fun getTourApplicationStatusFromFirebase() {
        val auth = FirebaseAuth.getInstance()
        val list = mutableListOf<TourAppealModel>()
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Application").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    list.clear()
                    for (doc in docs) {
                        val tourModel = (doc["tourModel"] as MutableMap<String, Any>).toTourModel()
                        val payment =
                            (doc["payment"] as MutableMap<String, Any>).toPaymentDataModel()
                        val status = doc["status"].toString()

                        val tourAppealModel = TourAppealModel(
                            tourModel,
                            payment,
                            status
                        )

                        list.add(tourAppealModel)
                    }
                    appeals.value = list
//                        .filter {
//                        it.payment.userModel.email == auth.currentUser!!.email!!
//                    }.toMutableList()
                }
            }
        }
    }
}