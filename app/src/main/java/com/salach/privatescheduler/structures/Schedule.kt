package com.salach.privatescheduler.structures

import com.salach.privatescheduler.enums.Period
import java.time.LocalDate
import java.time.LocalTime

data class Schedule (
    var startingDate: LocalDate? = null,
    var frequency: Int = 0,
    var period: Period = Period.NONE,
    var time: LocalTime? = null,
    var occurrences: Int = 0,
    var endDate: LocalDate? = null
){

}