package com.natiqhaciyef.voyagers.util.functions

import com.natiqhaciyef.voyagers.util.DefaultModelImplementations.ticketModel

fun fromDoubleToTimeLine(d: Double = 7.5): String{
    val hours = d.toInt()
    val minutes = (60 * (d - hours)).toInt()

    return "$hours hours\n$minutes minutes"
}

fun main() {
    println(fromDoubleToTimeLine(ticketModel.flightTime))
}