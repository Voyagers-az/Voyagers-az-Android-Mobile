package com.natiqhaciyef.voyagers.util.functions

import com.natiqhaciyef.voyagers.util.obj.DefaultModelImplementations


val date = DefaultModelImplementations.ticketModel.arrivalDate
fun splitterTimeDate(formattedDate: String): Map<String, String>{
    val map = mutableMapOf<String, String>()
    val time = formattedDate.substring(0 until 5)
    val date = formattedDate.substring(6 until formattedDate.length)

    map["time"] = time
    map["date"] = date
    return map
}

fun dateToLocalTime(formattedDate: String): String{
    val dayIndex = fromDateToDay(formattedDate)
    val day = formattedDate.substring(dayIndex until 2)
    val month = fromDateToMonth(formattedDate.substring(3 until 5))

    return "$day $month"
}

fun main() {
    println(dateToLocalTime("02.04.2023"))
    splitterTimeDate(date)
    println(fromDoubleToTimeLine(DefaultModelImplementations.ticketInfoModel.ticketModel.flightTime))

//    println("02.04.2023".substring(3 until 5))  // month
//    println("02.04.2023".substring(0 until 2))  // day
}

fun fromDateToMonth(month: String) = when(month){
    "01" -> { "Yanvar" }
    "02" -> { "Fevral" }
    "03" -> { "Mart" }
    "04" -> { "Aprel" }
    "05" -> { "May" }
    "06" -> { "Iyun" }
    "07" -> { "Iyul" }
    "08" -> { "Avqust" }
    "09" -> { "Sentyabr" }
    "10" -> { "Oktyabr" }
    "11" -> { "Noyabr" }
    "12" -> { "Dekabr" }
    else -> "Time left"
}

fun fromDateToDay(day: String): Int = if (day.startsWith("0")) 1 else 0


fun fromDoubleToTimeLine(d: Double = 7.5): String{
    val hours = d.toInt()
    val minutes = (60 * (d - hours)).toInt()

    return "$hours saat\n$minutes dəqiqə"
}
