package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.*
import com.natiqhaciyef.voyagers.data.model.UserModel

@Dao
interface AppDao {

    @Query("SELECT * FROM user_model WHERE email = :email")
    suspend fun getUser(email: String): UserModel?
    @Query("SELECT * FROM user_model")
    fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)
}