package com.natiqhaciyef.voyagers.view.viewmodel.tour

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.voyagers.data.local.repository.TourRepository
import com.natiqhaciyef.voyagers.data.model.*
import com.natiqhaciyef.voyagers.util.classes.DataTypes
import com.natiqhaciyef.voyagers.util.functions.toSQLiteMutableList
import com.natiqhaciyef.voyagers.util.functions.toSQLiteMutableMap
import com.natiqhaciyef.voyagers.util.functions.toSQLiteString
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TourDetailsViewModel @Inject constructor(
    var tr: TourRepository
) : BaseViewModel() {

    val savedTours = mutableStateOf<MutableList<TourModel>>(mutableListOf())
    val savedCamps = mutableStateOf<MutableList<CampModel>>(mutableListOf())
    private val savedToursDatabase = mutableStateOf<List<TourModelDatabase>>(mutableListOf())
    private val savedCampsDatabase = mutableStateOf<List<CampModelDatabase>>(mutableListOf())

    init {
        getSavedTours()
        getSavedCamps()
    }

    private fun getSavedTours(){
        viewModelScope.launch(Dispatchers.Main) {
            savedToursDatabase.value = tr.getTours()
            typeCasterTourModel()
        }
    }

    private fun typeCasterTourModel(){
        val list = mutableListOf<TourModel>()
        viewModelScope.launch(Dispatchers.Main) {
            for (element in savedToursDatabase.value){
                list.add(
                    TourModel(
                        id = element.id,
                        name = element.name,
                        image = element.image.toSQLiteMutableList(),
                        info = element.info,
                        companyName = element.companyName,
                        country = element.country,
                        route = element.route.toSQLiteMutableMap(),
                        price = element.price,
                        personCount = element.personCount,
                        rating = element.rating,
                        scope = element.scope,
                        location = element.location,
                        date = element.date.toSQLiteMutableMap(),
                        region = element.region
                    )
                )
            }
            savedTours.value = list
            Log.d("MyLog - 4","${savedTours.value}")
        }
    }


    fun saveTourModel(tourModel: TourModel){
        viewModelScope.launch(Dispatchers.Main) {
            tr.insertTour(
                TourModelDatabase(
                    id = tourModel.id,
                    name = tourModel.name,
                    image = tourModel.image.toSQLiteString(),
                    info = tourModel.info,
                    country = tourModel.country,
                    route = tourModel.route.toSQLiteString(),
                    price = tourModel.price,
                    personCount = tourModel.personCount,
                    rating = tourModel.rating,
                    scope = tourModel.scope,
                    location = tourModel.location,
                    companyName = tourModel.companyName,
                    date = tourModel.date.toSQLiteString(),
                    region = tourModel.region
                )
            )
        }
    }

    fun deleteTourModel(tourModel: TourModel){
        viewModelScope.launch(Dispatchers.Main) {
            tr.deleteTour(
                TourModelDatabase(
                    id = tourModel.id,
                    name = tourModel.name,
                    image = tourModel.image.toSQLiteString(),
                    info = tourModel.info,
                    country = tourModel.country,
                    route = tourModel.route.toSQLiteString(),
                    price = tourModel.price,
                    personCount = tourModel.personCount,
                    rating = tourModel.rating,
                    scope = tourModel.scope,
                    location = tourModel.location,
                    companyName = tourModel.companyName,
                    date = tourModel.date.toSQLiteString(),
                    region = tourModel.region
                )
            )
        }
    }


    fun saveCampModel(campModel: CampModel){
        viewModelScope.launch(Dispatchers.Main) {
            tr.insertCamp(
                CampModelDatabase(
                    id = campModel.id,
                    name = campModel.name,
                    info = campModel.info,
                    image = campModel.image,
                    location = campModel.location,
                    scope = campModel.scope,
                    country = campModel.country,
                    companyName = campModel.companyName,
                    price = campModel.price,
                    personCount = campModel.personCount,
                    region = campModel.region,
                    rating = campModel.rating,
                    date = campModel.date.toSQLiteString()
                )
            )
        }
    }

    fun deleteCampModel(campModel: CampModel){
        viewModelScope.launch(Dispatchers.Main) {
            tr.deleteCamp(
                CampModelDatabase(
                    id = campModel.id,
                    name = campModel.name,
                    info = campModel.info,
                    image = campModel.image,
                    location = campModel.location,
                    scope = campModel.scope,
                    country = campModel.country,
                    companyName = campModel.companyName,
                    price = campModel.price,
                    personCount = campModel.personCount,
                    region = campModel.region,
                    rating = campModel.rating,
                    date = campModel.date.toSQLiteString()
                )
            )
        }
    }

    private fun getSavedCamps(){
        viewModelScope.launch(Dispatchers.Main) {
            savedCampsDatabase.value = tr.getCamps().toMutableList()
            typeCasterCampModel()
        }
    }

    private fun typeCasterCampModel(){
        val list = mutableListOf<CampModel>()
        viewModelScope.launch(Dispatchers.Main) {
            for (element in savedCampsDatabase.value){
                list.add(
                    CampModel(
                        id = element.id,
                        name = element.name,
                        info = element.info,
                        image = element.image,
                        location = element.location,
                        scope = element.scope,
                        country = element.country,
                        companyName = element.companyName,
                        price = element.price,
                        personCount = element.personCount,
                        region = element.region,
                        rating = element.rating,
                        date = element.date.toSQLiteMutableMap()
                    )
                )
            }
            savedCamps.value = list
        }
    }


    fun dataTypeCaster(data: Any): DataTypes = when (data) {
        is TourModel -> {
            DataTypes.TourModel
        }
        is CampModel -> {
            DataTypes.CampModel
        }
        is PlaceModel -> {
            DataTypes.PlaceModel
        }
        else -> {
            DataTypes.NonSelected
        }
    }
}