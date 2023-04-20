package com.natiqhaciyef.voyagers.data.model.car

data class CarModel(
    var id: Int,
    var name: String,
    var brand: String,
    var image: String,
    var engine: Double,
    var year: Int,
    var description: String,
){

    fun carModelToMap(): Map<String, String>{
        return mapOf(
            "id" to "$id",
            "name" to "$name",
            "brand" to "$brand",
            "image" to "$image",
            "engine" to "$engine",
            "year" to "$year",
            "description" to "$description"
        )
    }


    companion object{
        fun mapToCarModel(map: Map<String, String>): CarModel {
            return CarModel(
                id = map["id"]!!.toInt(),
                name = map["name"]!!.toString(),
                brand = map["brand"]!!.toString(),
                image = map["image"]!!.toString(),
                engine = map["engine"]!!.toDouble(),
                year = map["year"]!!.toInt(),
                description = map["description"].toString()
            )
        }
    }
}
