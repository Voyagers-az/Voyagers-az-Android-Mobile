package com.natiqhaciyef.voyagers.util.obj

import androidx.compose.runtime.mutableStateOf
import com.natiqhaciyef.voyagers.data.model.*
import com.natiqhaciyef.voyagers.data.model.car.CarModel
import com.natiqhaciyef.voyagers.data.model.car.CarRentModel
import com.natiqhaciyef.voyagers.data.model.db.FirebaseUserModel
import com.natiqhaciyef.voyagers.data.model.enums.Luggage
import com.natiqhaciyef.voyagers.data.model.enums.PaymentTypes
import com.natiqhaciyef.voyagers.data.model.enums.RegionSide
import com.natiqhaciyef.voyagers.data.model.enums.TourScope
import com.natiqhaciyef.voyagers.data.model.flight.CombinedTicketModel
import com.natiqhaciyef.voyagers.data.model.flight.FlightTransfer
import com.natiqhaciyef.voyagers.data.model.flight.TicketInfoModel
import com.natiqhaciyef.voyagers.data.model.flight.TicketModel
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.tour.CampModel
import com.natiqhaciyef.data.data.model.tour.PlaceModel
import com.natiqhaciyef.voyagers.data.model.tour.TourModel
import java.text.SimpleDateFormat
import java.util.*

object DefaultModelImplementations {
    var data = Any()
    val selectedIndex = mutableStateOf(0)   // nav index

    val carModel = CarModel(
        id = 0,
        name = "Audi",
        brand = "A8 Long",
        image = "https://gaycarboys.com/wp-content/uploads/2020/08/all-new-2020-Audi-S8-1-scaled.jpg",
        engine = 4.3,
        year = 2021,
        description = "Audi A8 is the best car in the world"
    )

    val carRentModel = CarRentModel(
        id = 1,
        place = "Bakı",
        car = carModel,
        dailyPrice = 76.0,
        priceType = "AZN",
        time = "2 gün",
        ownerInfo = "Natiq Haciyev"
    )


    val userModel = UserModel(
        id = 0,
        name = "Natiq",
        email = "natiq00h2272@gmail.com",
        surname = "Haciyev",
        dateOfBirth = "08.06.2003",
        phone = "+994-55-386-0054",
        idNumber = "923082F",
        visaImage = "",
        idImage = "",
        password = "12345"
    )

    val userModelEmpty = UserModel(
        id = 0,
        name = "",
        email = "",
        surname = "",
        dateOfBirth = "",
        phone = "",
        idNumber = "",
        visaImage = "",
        idImage = "",
        password = ""
    )

    val firebaseUserModel = FirebaseUserModel(
        username = "Natiq",
        email = "@gmail.com",
        phone = "88888888",
        password = "Natiq123"
    )

    var place: PlaceModel = PlaceModel(
        id = 0,
        name = "Baki",
        image = mutableListOf(""),
        scope = TourScope.Global.scope,
        side = RegionSide.North.side,
        rating = 4.5
    )

    var tourModel: TourModel = TourModel(
        id = 4,
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
        date = mutableMapOf("21 June" to "24 June"),
        isLiked = false
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
        location = "Şamaxı",
        isLiked = false
    )

    val flightTransfer = FlightTransfer(
        id = 0,
        landedPlace = "Istanbul",
        waitingTime = 1.5,
        arrivalDate = "07:40 07.06.2023",
        departureDate = "09:20 07.06.2023",
        landedCountry = "Türkiyə"
    )

    var ticketModel = TicketModel(
        price = 942.0,
        info = "Airbus A321neo da uçuş 3s 10d. Dar gövdə",
        image = "https://media.cntraveler.com/photos/5b914e80d5806340ca438db1/1:1/w_2250,h_2250,c_limit/BrandenburgGate_2018_GettyImages-549093677.jpg",
        departureDate = "05:35 07.06.2023",
        arrivalDate = "13:05 07.06.2023",
        fromCity = "Bakı",
        toCity = "Berlin",
        flightTime = 7.5,
        companyNames = mutableListOf("Anadolu Jet"),
        transfer = flightTransfer,
        luggage = Luggage.Medium.name.lowercase(),
        fromCountry = "Azərbaycan",
        toCountry = "Almanya"
    )

    val ticketInfoModel = TicketInfoModel(
        id = 0,
        userInfo = userModel,
        depTicketModel = ticketModel,
        retTicketModel = ticketModel
    )

    val paymentDataModel = PaymentDataModel(
        id = 0,
        paymentType = PaymentTypes.VISA.mainName,
        nameOnCard = "Natig Haciyev",
        numberOnCard = "9329 9092 6483 8884",
        expirationDate = "07/27",
        cvvCode = 992,
        userModel = userModel
    )

    val combinedTicketModel = CombinedTicketModel(
        depTicketModel = TicketModel(
            price = 510.0,
            info = "Airbus A321neo da uçuş 3s 10d. Dar gövdə",
            image = "https://media.cntraveler.com/photos/5b914e80d5806340ca438db1/1:1/w_2250,h_2250,c_limit/BrandenburgGate_2018_GettyImages-549093677.jpg",
            departureDate = "05:35 07.06.2023",
            arrivalDate = "13:05 07.06.2023",
            fromCity = "Bakı",
            toCity = "Berlin",
            flightTime = 7.5,
            companyNames = mutableListOf("Anadolu Jet"),
            transfer = flightTransfer,
            luggage = Luggage.Medium.name.lowercase(),
            fromCountry = "Azərbaycan",
            toCountry = "Almanya"
        ),
        retTicketModel = TicketModel(
            price = 455.0,
            info = "Airbus A321neo da uçuş 3s 10d. Dar gövdə",
            image = "https://www.thediaryofanomad.com/wp-content/uploads/2020/08/best-places-visit-in-baku-cityscape.jpg",
            departureDate = "12:10 12.06.2023",
            arrivalDate = "20:35 12.06.2023",
            toCity = "Bakı",
            fromCity = "Berlin",
            flightTime = 8.25,
            companyNames = mutableListOf("Anadolu Jet"),
            transfer = flightTransfer,
            luggage = Luggage.Medium.name.lowercase(),
            toCountry = "Azərbaycan",
            fromCountry = "Almanya"
        )
    )

    private fun dateTime(): String {
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy")
        return sdf.format(date)
    }
}
