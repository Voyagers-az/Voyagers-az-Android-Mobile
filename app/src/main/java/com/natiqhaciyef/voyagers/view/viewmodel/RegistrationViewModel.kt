package com.natiqhaciyef.voyagers.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.interviewsland_mobile_android.util.Resource
import com.natiqhaciyef.voyagers.data.local.repository.LocalRepository
import com.natiqhaciyef.voyagers.data.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    val repository: LocalRepository
) : BaseViewModel() {
    val auth = Firebase.auth
    val firestore = Firebase.firestore
    var userState = mutableStateOf(UserModel(id = 0, name = "", email = "", phone = "", password = ""))
    var allUsersState = mutableStateOf<List<UserModel>>(listOf())
    var resultMessage = mutableStateOf("")

    init {
        getAllUsers()
    }

    fun getUser(email: String) = viewModelScope.launch(Dispatchers.Main) {
        val user = repository.getUser(email)
        if (user != null) {
            userState.value = user
        } else {
            userState.value = UserModel(id = 0, name = "", email = "", phone = "", password = "")
        }
    }

    private fun getAllUsers() = viewModelScope.launch(Dispatchers.Main) {
        val users = repository.getAllUsers()
        if (users.isNotEmpty() || users != null)
            allUsersState.value = users
        else
            allUsersState.value = listOf()
    }

    fun insertUser(userModel: UserModel) = viewModelScope.launch(Dispatchers.Main) {
        repository.insertUser(userModel)
    }

    fun deleteUser(userModel: UserModel) = viewModelScope.launch(Dispatchers.Main) {
        repository.deleteUser(userModel)
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