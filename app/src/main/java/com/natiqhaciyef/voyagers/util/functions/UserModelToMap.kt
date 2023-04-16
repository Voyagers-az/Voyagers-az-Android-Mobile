package com.natiqhaciyef.voyagers.util.functions

import com.natiqhaciyef.voyagers.data.model.UserModel

fun UserModel.toMapForFirebase(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    map["id"] = this.id
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


fun Map<String, Any>.toUserModelForFirebase(): UserModel {
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


fun String.nameSurnameSplitter(): Map<String, String>{
    val input = this
    var name = ""
    var surname = ""

    for (char in input.trim()){
        if (char != ' ')
            surname += char
        else{
            name = surname
            surname = ""
        }
    }

    return mutableMapOf("name" to name, "surname" to surname)
}