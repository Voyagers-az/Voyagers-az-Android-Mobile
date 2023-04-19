package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.voyagers.data.model.*
import com.natiqhaciyef.voyagers.data.model.db.CampModelDatabase
import com.natiqhaciyef.voyagers.data.model.db.PaymentDBModel
import com.natiqhaciyef.voyagers.data.model.db.TourModelDatabase

@Database(
    entities = [UserModel::class, TourModelDatabase::class, CampModelDatabase::class, PaymentDBModel::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): AppDao
    abstract fun getTourDao(): TourDao
}