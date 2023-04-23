package com.natiqhaciyef.voyagers.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(

) : BaseViewModel() {
    val auth = Firebase.auth
    val firestore = Firebase.firestore
    val fums = mutableStateOf<List<FirebaseUserModel>>(mutableListOf())
    var userState = mutableStateOf(UserModel(id = 0, name = "", email = "", phone = "", password = ""))
    var allUsersState = mutableStateOf<List<UserModel>>(listOf())
    var resultMessage = mutableStateOf("")

    init {
        getAllUsersFromFirebase()
    }
    fun getAllUsersFromFirebase(){
        val list = mutableListOf<FirebaseUserModel>()
        viewModelScope.launch(Dispatchers.IO) {
            firestore.collection("Users").addSnapshotListener{value, error ->
                if (value != null && !value.isEmpty){
                    val docs = value.documents
                    list.clear()
                    for (doc in docs){
                        val username = doc["username"].toString()
                        val email = doc["email"].toString()
                        val phone = doc["phone"].toString()

                        val fum = FirebaseUserModel(username,email,phone)
                        list.add(fum)
                    }
                    fums.value = list
                }
            }
        }
    }

    fun registerUser(email: String, password: String, username: String, phone: String, content: () -> Unit){
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val userMap = hashMapOf<String,Any>()
            userMap["username"] = username
            userMap["email"] = email
            userMap["phone"] = phone

            firestore.collection("Users").document(email).set(userMap)
                .addOnSuccessListener {
                    resultMessage.value = "Success storing data"
                    content()
                }.addOnFailureListener {
                    resultMessage.value = "Fail storing data"
                }
        }.addOnFailureListener {
            resultMessage.value = "Fail register"
        }
    }

    fun loginUser(email: String, password: String, content: ()-> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                resultMessage.value = "Success login"
                content()
            }.addOnSuccessListener {
                resultMessage.value = "Fail login"
            }
    }

    fun resetPasswordUser(email: String, content: () -> Unit){
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            resultMessage.value = "Success email link sended"
            content()
        }.addOnFailureListener {
            resultMessage.value = "Fail email link sended"
        }
    }
}