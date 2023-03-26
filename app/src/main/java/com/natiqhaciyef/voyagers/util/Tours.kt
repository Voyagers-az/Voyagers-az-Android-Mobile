package com.natiqhaciyef.voyagers.util

import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.RegionSide
import com.natiqhaciyef.voyagers.data.model.enums.TourScope

object Tours {
    val list = mutableListOf(
        TourModel(
            id = 1,
            name = "Avia",
            image = mutableListOf("https://img.fotocommunity.com/qebele-azerbaijan-6372766b-168f-4fea-891b-f2afc2981167.jpg?height=1080"),
            info = "Qəbələ turu. Gediş-gəliş, səhər yeməyi daxil",
            route = mutableMapOf("Baku" to "Qebele", "Qebele" to "Baku"),
            price = 20.0,
            personCount = 25,
            rating = 4.5,
            country = "Azerbaijan",
            scope = TourScope.Local.scope
        ),
        TourModel(
            id = 2,
            name = "Voyager",
            image = mutableListOf("https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg"),
            info = "Quba turu, Çənlibel gölü. Gediş-gəliş, səhər yeməyi daxil",
            route = mutableMapOf("Baku" to "Quba", "Quba" to "Baku"),
            price = 20.0,
            personCount = 25,
            rating = 4.5,
            country = "Azerbaijan",
            scope = TourScope.Local.scope
        )
    )
}


object Places {
    var list = mutableListOf(
        PlaceModel(
            id = 0,
            name = "Qəbələ",
            image = "https://img.fotocommunity.com/qebele-azerbaijan-6372766b-168f-4fea-891b-f2afc2981167.jpg?height=1080",
            scope = TourScope.Local.scope,
            side = RegionSide.North.side,
            rating = 4.5
        ),
        PlaceModel(
            id = 0,
            name = "Quba",
            image = "https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg",
            scope = TourScope.Local.scope,
            side = RegionSide.North.side,
            rating = 4.5
        )
    )
}