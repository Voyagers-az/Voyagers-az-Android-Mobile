package com.natiqhaciyef.voyagers.view.viewmodel.tour

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.voyagers.data.local.repository.TourRepository
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.TourModelDatabase
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
    val savedToursDatabase = mutableStateOf<List<TourModelDatabase>>(mutableListOf())

    init {
        getSavedTours()
    }

    private fun getSavedTours(){
        viewModelScope.launch(Dispatchers.Main) {
            savedToursDatabase.value = tr.getTours().toMutableList()
            typeCasterTourModel()
        }
    }

    private fun typeCasterTourModel(){
        viewModelScope.launch(Dispatchers.Main) {
            for (element in savedToursDatabase.value){
                savedTours.value.add(
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


    fun dataTypeCaster(data: Any): DataTypes = when (data) {
        is com.natiqhaciyef.voyagers.data.model.TourModel -> {
            DataTypes.TourModel
        }
        is com.natiqhaciyef.voyagers.data.model.CampModel -> {
            DataTypes.CampModel
        }
        is com.natiqhaciyef.voyagers.data.model.PlaceModel -> {
            DataTypes.PlaceModel
        }
        else -> {
            DataTypes.NonSelected
        }
    }
}