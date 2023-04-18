package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.*
import com.natiqhaciyef.voyagers.data.model.UserModel

@Dao
interface AppDao {

    @Query("SELECT * FROM user_model WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserModel?

    @Query("SELECT * FROM user_model")
    fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)

    @Query("SELECT * FROM user_model WHERE name = :username")
    suspend fun getUserByName(username: String): UserModel?
}