package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.voyagers.data.model.*

@Database(entities = [UserModel::class, TourModelDatabase::class, CampModelDatabase::class], version = 3)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao(): AppDao
    abstract fun getTourDao(): TourDao
}