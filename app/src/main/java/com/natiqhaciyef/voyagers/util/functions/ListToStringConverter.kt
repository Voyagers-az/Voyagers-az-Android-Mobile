package com.natiqhaciyef.voyagers.util.functions

import android.util.Log


fun main() {
    val list = mutableListOf(
        "https://i.ytimg.com/vi/0vSvcf39WzE/maxresdefault.jpg",
    )
    println("${list.toSQLiteString()}")
    println("${list.toSQLiteString().toSQLiteMutableList()}")
}
fun MutableList<String>.toSQLiteString(): String {
    var str = ""
    for (element in this) {
        str += element
        str += "#"
    }
    return str
}


fun String.toSQLiteMutableList(): MutableList<String> {
    val list = mutableListOf<String>()
    var word = ""
    for (element in this) {
        if (element != '#')
            word += element
        else{
            list.add(word)
            word = ""
        }
    }

    return list
}