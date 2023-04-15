package com.natiqhaciyef.voyagers.data.local.repository

import com.natiqhaciyef.voyagers.data.local.source.TourDataSource
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase

class TourRepository(var ds: TourDataSource) {

    suspend fun getTours() = ds.getTours()

    suspend fun insertTour(tourModel: TourModelDatabase) = ds.insertTour(tourModel)

    suspend fun deleteTour(tourModel: TourModelDatabase) = ds.deleteTour(tourModel)

}