package com.natiqhaciyef.voyagers.data.local.repository

import com.natiqhaciyef.voyagers.data.local.source.TourDataSource
import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.CampModelDatabase
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase

class TourRepository(var ds: TourDataSource) {

    suspend fun getTours() = ds.getTours()

    suspend fun insertTour(tourModel: TourModelDatabase) = ds.insertTour(tourModel)

    suspend fun deleteTour(tourModel: TourModelDatabase) = ds.deleteTour(tourModel)


    suspend fun getCamps() = ds.getCamps()

    suspend fun insertCamp(campModel: CampModelDatabase) = ds.insertCamp(campModel)

    suspend fun deleteCamp(campModel: CampModelDatabase) = ds.deleteCamp(campModel)

}