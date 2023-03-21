package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.voyagers.data.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao(): AppDao
}