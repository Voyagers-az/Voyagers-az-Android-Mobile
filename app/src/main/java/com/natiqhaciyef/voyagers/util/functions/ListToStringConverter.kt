package com.natiqhaciyef.voyagers.util.functions

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