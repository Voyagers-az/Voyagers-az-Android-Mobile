package com.natiqhaciyef.voyagers.util

import com.natiqhaciyef.voyagers.data.model.CampModel
import com.natiqhaciyef.voyagers.data.model.PlaceModel
import com.natiqhaciyef.voyagers.data.model.TourModel
import com.natiqhaciyef.voyagers.data.model.enums.RegionSide
import com.natiqhaciyef.voyagers.data.model.enums.TourScope

object DefaultModelImplementations {
    var place: PlaceModel = PlaceModel(
        id = 0,
        name = "Baki",
        image = "",
        scope = TourScope.Global.scope,
        side = RegionSide.North.side,
        rating = 4.5
    )

    var tourModel: TourModel = TourModel(
        id = 0,
        name = "Quba",
        image = mutableListOf("https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg"),
        info = "Quba turu, Çənlibel gölü. Gediş-gəliş, səhər yeməyi daxil",
        country = "Azerbaijan",
        route = mutableMapOf("Baku" to "Quba"),
        price = 20.0,
        personCount = 20,
        rating = 4.3,
        scope = TourScope.Local.scope,
        companyName = "Voyagers",
        region = RegionSide.North.side
    )

    var campModel: CampModel = CampModel(
        id = 0,
        name = "Quba",
        image = "https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg",
        info = "Quba turu, Çənlibel gölü. Gediş-gəliş, səhər yeməyi daxil",
        country = "Azerbaijan",
        date = mutableMapOf("20-03-2023" to "27-03-2023"),
        price = 20.0,
        personCount = 20,
        rating = 4.3,
        scope = TourScope.Local.scope,
        companyName = "Voyagers",
        region = RegionSide.North.side,
        location = "Şamaxı"
    )

    var data = Any()
}