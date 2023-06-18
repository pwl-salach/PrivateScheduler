package com.salach.privatescheduler.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TimeUtils {

    companion object {
        fun getTimeFromString(dateString: String): LocalTime {
            return LocalTime.parse(dateString, getTimeFormat())
        }

        fun getTimeFormatString(): String{
            val locale = Locale.getDefault()
            return when (locale.country){
                "US" -> "hh:mm"
                else -> "HH:mm"
            }
        }

        fun getTimeFormat(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern(getTimeFormatString())
        }
    }
}