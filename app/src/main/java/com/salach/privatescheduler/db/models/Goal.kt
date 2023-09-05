package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Goal (
    @ColumnInfo var icon: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val subtitle: String,
    @ColumnInfo var progress: Float,
    @PrimaryKey var id: Long = 0
)
