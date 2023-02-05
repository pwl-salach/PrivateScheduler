package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo val name: String,
    @ColumnInfo val iconShape: Int,
    @ColumnInfo val iconColor: Int,
    @PrimaryKey val id: Int? = null,
)
