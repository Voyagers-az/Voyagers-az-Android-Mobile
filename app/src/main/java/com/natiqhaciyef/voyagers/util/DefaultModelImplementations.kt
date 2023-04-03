package com.natiqhaciyef.voyagers.util

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        id = "4",
        name = "Barselona turu",
        image = mutableListOf("https://firebasestorage.googleapis.com/v0/b/voyagers-666fb.appspot.com/o/Tour%2Fglobal%2Fbarcelona.jpg?alt=media&token=bd9aeb35-77bb-41a8-b930-1c4fe1d06d41"),
        info = "Barselona şəhəri İspaniyanın Kataloniya bölgəsinin ən məşhur şəhəridir. Barselona tarixi tikililəri, şəhər quruluşu və futbol mədəniyyəti ilə məşhurdur. Həmçinin liman şəhəri olması ilə bir çox mədəniyyətin qarışdığı şəhər halını almışdır. Artıq Barselona turu əlçatandır, elə isə fürsəti dəyərləndir...\n" + "Toplam 3 gün, 4 gecə olmaqla Barselonada gəzmək şansı. Tura daxildir : Otel xərcləri, Səhər yeməyi, Bələdçi və gəziləcək yerlərin qiyməti",
        country = "Ispaniya",
        route = mutableMapOf("Bakı" to "Barselona"),
        price = 1650.0,
        personCount = 10,
        rating = 4.8,
        scope = TourScope.Global.scope,
        location = "Ispaniya, Kataloniya, Barselona",
        companyName = "Voyagers",
        region = RegionSide.Europe.side,
        date = mutableMapOf("21 June" to "24 June")
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

    // nav index
    val selectedIndex = mutableStateOf(0)
}