package com.natiqhaciyef.voyagers.util.functions

import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations.ticketInfoModel

fun fromDoubleToTimeLine(d: Double = 7.5): String{
    val hours = d.toInt()
    val minutes = (60 * (d - hours)).toInt()

    return "$hours saat\n$minutes dəqiqə"
}

fun main() {
    println(fromDoubleToTimeLine(ticketInfoModel.ticketModel.flightTime))
}