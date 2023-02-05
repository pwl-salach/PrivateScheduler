package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey
import com.salach.privatescheduler.enums.TimeUnit
import java.time.LocalDateTime

@Entity
data class Schedule (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val everyNth: Int,
    @ColumnInfo val timeUnit: TimeUnit,
    @ColumnInfo val startDttm: LocalDateTime,
    @ColumnInfo val endDttm: LocalDateTime?
)
