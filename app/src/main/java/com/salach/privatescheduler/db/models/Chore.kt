package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Note::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("noteId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Chore(
    @ColumnInfo(index = true) val noteId: Int,
    @ColumnInfo val shortDesc: String,
    @ColumnInfo val done: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
