package com.natiqhaciyef.voyagers.data.local.source

import com.natiqhaciyef.voyagers.data.model.CampModelDatabase
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase
import com.natiqhaciyef.voyagers.data.roomdb.TourDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TourDataSource(var tourDao: TourDao) {

    suspend fun getTours() = withContext(Dispatchers.IO){
        tourDao.getTourModels()
    }

    suspend fun insertTour(tourModel: TourModelDatabase) = withContext(Dispatchers.IO){
        tourDao.insertTourModel(tourModel)
    }

    suspend fun deleteTour(tourModel: TourModelDatabase) = withContext(Dispatchers.IO){
        tourDao.deleteTourModel(tourModel)
    }

    suspend fun getCamps() = withContext(Dispatchers.IO){
        tourDao.getCampModels()
    }

    suspend fun insertCamp(campModel: CampModelDatabase) = withContext(Dispatchers.IO){
        tourDao.insertCampModel(campModel)
    }

    suspend fun deleteCamp(campModel: CampModelDatabase) = withContext(Dispatchers.IO){
        tourDao.deleteCampModel(campModel)
    }
}