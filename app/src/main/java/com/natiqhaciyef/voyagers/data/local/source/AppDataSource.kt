package com.natiqhaciyef.voyagers.data.local.source

import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.data.roomdb.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppDataSource(val dao: AppDao) {

    suspend fun getUserByEmail(email: String) = withContext(Dispatchers.IO){
        dao.getUserByEmail(email)
    }

    suspend fun getUserByName(usernmae: String) = withContext(Dispatchers.IO){
        dao.getUserByName(usernmae)
    }

    suspend fun getAllUsers() = withContext(Dispatchers.IO){
        dao.getAllUsers()
    }

    suspend fun insertUser(userModel: UserModel) = withContext(Dispatchers.IO){
        dao.insertUser(userModel)
    }

    suspend fun deleteUser(userModel: UserModel) = withContext(Dispatchers.IO){
        dao.deleteUser(userModel)
    }

}