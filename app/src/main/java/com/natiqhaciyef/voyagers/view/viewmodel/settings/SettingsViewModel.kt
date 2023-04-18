package com.natiqhaciyef.voyagers.view.viewmodel.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.local.repository.LocalRepository
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : BaseViewModel() {
    val firestore = Firebase.firestore
    val fumList = mutableStateOf<MutableList<FirebaseUserModel>>(mutableListOf())

    init {
        getUsernameFromFirebase()
    }

    fun getUsernameFromFirebase(){
        val list = mutableListOf<FirebaseUserModel>()
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Users").addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty){
                    val docs = value.documents
                    list.clear()
                    for (doc in docs){
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

    fun filter(userName: String): MutableList<FirebaseUserModel>{
        return if (fumList.value.isNotEmpty()){
            fumList.value.filter {
                it.username == userName
            }.toMutableList()
        }else{
            mutableListOf()
        }
    }
}