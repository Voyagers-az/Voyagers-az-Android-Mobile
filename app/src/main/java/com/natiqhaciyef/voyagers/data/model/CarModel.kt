package com.natiqhaciyef.voyagers.data.model

data class CarModel(
    var id: Int,
    var name: String,
    var brand: String,
    var engine: Double,
    var description: String,
){

    fun carModelToMap(): Map<String, String>{
        return mapOf(
            "id" to "$id",
            "name" to "$name",
            "brand" to "$brand",
            "engine" to "$engine",
            "description" to "$description"
        )
    }


    companion object{
        fun mapToCarModel(map: Map<String, String>): CarModel{
            return CarModel(
                id = map["id"]!!.toInt(),
                name = map["name"]!!.toString(),
                brand = map["brand"]!!.toString(),
                engine = map["engine"]!!.toDouble(),
                description = map["description"].toString()
            )
        }
    }
}
