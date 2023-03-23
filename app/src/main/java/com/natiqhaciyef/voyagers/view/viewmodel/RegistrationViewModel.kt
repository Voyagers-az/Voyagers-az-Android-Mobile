package com.natiqhaciyef.voyagers.view.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {
    var userState = mutableStateOf(UserModel(id = 0, name = "", email = "", phone = "", password = ""))
    var allUsersState = mutableStateOf<List<UserModel>>(listOf())

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
}