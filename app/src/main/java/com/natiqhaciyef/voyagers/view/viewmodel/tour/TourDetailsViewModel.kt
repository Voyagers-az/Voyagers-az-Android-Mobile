package com.natiqhaciyef.voyagers.view.viewmodel.tour

import com.natiqhaciyef.voyagers.util.classes.DataTypes
import com.natiqhaciyef.voyagers.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TourDetailsViewModel @Inject constructor(

) : BaseViewModel() {

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