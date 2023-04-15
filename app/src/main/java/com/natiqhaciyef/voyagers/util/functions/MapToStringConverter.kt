package com.natiqhaciyef.voyagers.util.functions


fun main() {
    val map = mutableMapOf("start" to "21 June", "end" to "24 June")
    println(map.toSQLiteString())
    println(map.toSQLiteString().toSQLiteMutableMap())
}

fun Map<String, String>.toSQLiteString(): String{
    val keys = this.keys
    var str = ""

    for (key in keys){
        str += key
        str += ":"
        str += this[key]
        str += "%"
    }
    return str
}


fun String.toSQLiteMutableMap(): MutableMap<String, String>{
    var map = mutableMapOf<String, String>()
    val list = mutableListOf<String>()
    var vanishData = ""
    var value = ""
    var key = ""


    for (char in this){
        if (char != '%')
            vanishData += char
        else{
            list.add(vanishData)
            vanishData = ""
        }
    }

    for (vd in list){
        for (char in vd){
            if (char != ':')
                value += char
            else {
                key = value
                value = ""
            }
        }
        map[key] = value
        key = ""
        value = ""
    }
    return map
}