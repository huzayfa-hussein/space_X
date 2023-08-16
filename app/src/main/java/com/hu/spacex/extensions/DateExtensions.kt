package com.hu.spacex.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit


const val DATE_FORMAT = "dd MMM yyyy"
const val TIME_FORMAT = "hh:mm aaa"

fun Long.getFormattedDate(
    format: String = DATE_FORMAT,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    return try {
        val sdf: DateFormat = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = timeZone
        val netDate = Date(this)
        sdf.format(netDate)

    } catch (ex: Exception) {
        "xx"
    }
}

fun Long.calculateDays(): String {
    val timeInMillis = System.currentTimeMillis() - this
    return TimeUnit.MILLISECONDS.toDays(timeInMillis).toString()
}

fun Long.isInPast(): Boolean = (System.currentTimeMillis() - this) > 0

