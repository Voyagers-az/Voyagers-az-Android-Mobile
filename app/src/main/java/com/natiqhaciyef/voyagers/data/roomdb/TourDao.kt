package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.*
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.CampModelDatabase
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase

@Dao
interface TourDao {

    @Query("SELECT * FROM tour_model")
    suspend fun getTourModels(): MutableList<TourModelDatabase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTourModel(tourModel: TourModelDatabase)

    @Delete
    suspend fun deleteTourModel(tourModel: TourModelDatabase)


    @Query("SELECT * FROM tour_model")
    suspend fun getCampModels(): List<CampModelDatabase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCampModel(campModel: CampModelDatabase)

    @Delete
    suspend fun deleteCampModel(campModel: CampModelDatabase)
}