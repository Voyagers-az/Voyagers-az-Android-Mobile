package com.natiqhaciyef.voyagers.util.functions

import com.natiqhaciyef.voyagers.data.model.UserModel
import com.natiqhaciyef.voyagers.data.model.payment.PaymentDataModel
import com.natiqhaciyef.voyagers.data.model.tour.TourModel


fun TourModel.toMapForFirebase(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["id"] = this.id
    map["name"] = this.name
    map["image"] = this.image
    map["info"] = this.info
    map["country"] = this.country
    map["route"] = this.route
    map["price"] = this.price
    map["personCount"] = this.personCount
    map["rating"] = this.rating
    map["scope"] = this.scope
    map["location"] = this.location
    map["companyName"] = this.companyName
    map["date"] = this.date
    map["region"] = this.region
    map["isLiked"] = this.isLiked

    return map
}

fun Map<String, Any>.toTourModel(): TourModel {
    return TourModel(
        id = this["id"].toString().toInt(),
        name = this["name"].toString(),
        image = this["image"] as MutableList<String>,
        info = this["info"].toString(),
        country = this["country"].toString(),
        route = this["route"] as MutableMap<String, String>,
        price = this["price"].toString().toDouble(),
        personCount = this["personCount"].toString().toInt(),
        rating = this["rating"].toString().toDouble(),
        scope = this["scope"].toString(),
        location = this["location"].toString(),
        companyName = this["companyName"].toString(),
        date = this["date"] as MutableMap<String, String>,
        region = this["region"].toString(),
        isLiked = this["isLiked"].toString().toBoolean()
    )
}

fun PaymentDataModel.toMapForFirebase(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["id"] = this.id
    map["paymentType"] = this.paymentType
    map["nameOnCard"] = this.nameOnCard
    map["numberOnCard"] = this.numberOnCard
    map["expirationDate"] = this.expirationDate
    map["cvvCode"] = this.cvvCode
    map["userModel"] = this.userModel.toMapForFirebase()

    return map
}

fun Map<String, Any>.toPaymentDataModel(): PaymentDataModel {
    return PaymentDataModel(
        id = this["id"].toString().toInt(),
        paymentType = this["paymentType"].toString(),
        nameOnCard = this["nameOnCard"].toString(),
        numberOnCard = this["numberOnCard"].toString(),
        expirationDate = this["expirationDate"].toString(),
        cvvCode = this["cvvCode"].toString().toInt(),
        userModel = (this["userModel"] as Map<String, String>).toUserModelForFirebase()
    )
}


fun UserModel.toMapForFirebase(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    map["id"] = this.id.toString()
    map["name"] = this.name
    map["surname"] = this.surname
    map["dateOfBirth"] = this.dateOfBirth
    map["email"] = this.email
    map["phone"] = this.phone
    map["password"] = this.password
    map["finCode"] = this.idNumber
    map["idImage"] = this.idImage
    map["visaImage"] = this.visaImage

    return map
}


fun Map<String, String>.toUserModelForFirebase(): UserModel {
    return UserModel(
        id = this["id"].toString().toInt(),
        name = this["name"].toString(),
        surname = this["surname"].toString(),
        email = this["email"].toString(),
        phone = this["phone"].toString(),
        password = this["password"].toString(),
        dateOfBirth = this["dateOfBirth"].toString(),
        idNumber = this["idNumber"].toString(),
        idImage = this["idImage"].toString(),
        visaImage = this["visaImage"].toString()
    )
}


fun String.nameSurnameSplitter(): Map<String, String> {
    val input = this
    var name = ""
    var surname = ""

    for (char in input.trim()) {
        if (char != ' ')
            surname += char
        else {
            name = surname
            surname = ""
        }
    }

    return mutableMapOf("name" to name, "surname" to surname)
}