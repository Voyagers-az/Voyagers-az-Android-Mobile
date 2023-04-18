package com.natiqhaciyef.voyagers.data.local.repository

import com.natiqhaciyef.voyagers.data.local.source.AppDataSource
import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.data.roomdb.AppDatabase

class LocalRepository(val ds: AppDataSource) {

    suspend fun getUserByEmail(email: String) = ds.getUserByEmail(email)

    suspend fun getUserByName(username: String) = ds.getUserByName(username)

    suspend fun getAllUsers() = ds.getAllUsers()

    suspend fun insertUser(userModel: UserModel) = ds.insertUser(userModel)

    suspend fun deleteUser(userModel: UserModel) = ds.deleteUser(userModel)

}