package com.natiqhaciyef.voyagers.data.roomdb

import androidx.room.*
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase

@Dao
interface TourDao {

    @Query("SELECT * FROM tour_model")
    suspend fun getTourModels(): List<TourModelDatabase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTourModel(tourModel: TourModelDatabase)

    @Delete
    suspend fun deleteTourModel(tourModel: TourModelDatabase)
}