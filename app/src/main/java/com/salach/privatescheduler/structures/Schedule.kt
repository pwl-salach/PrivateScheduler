package com.salach.privatescheduler.structures

import com.salach.privatescheduler.enums.Period
import java.time.Instant
import java.util.Date

class Schedule {
    var startingDate: Date? = null
    var frequency: Int = 0
    var period: Period = Period.NONE
    var time: Instant? = null
    var occurrences = 0
    var endDate: Date? = null
}