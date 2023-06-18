package com.salach.privatescheduler.utils

import android.icu.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


class DateUtils {

    companion object {
        fun formatDate(date: Date): String {
            return SimpleDateFormat(getDateFormatString(), Locale.getDefault()).format(date)
        }

        fun formatDate(year: Int, month: Int, day: Int): String {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return formatDate(calendar.time)
        }

        fun getDateFromString(dateString: String): LocalDate{
            return LocalDate.parse(dateString, getDateFormat())
        }

        fun getDateFormatString(): String{
            val locale = Locale.getDefault()
            return when (locale.country){
                "US" -> "MM/dd/yyyy"
                else -> "dd/MM/yyyy"
            }
        }

        fun getDateFormat(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern(getDateFormatString())
        }
    }
}