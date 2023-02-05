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
data class Memo(
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT) val text: String,
    @ColumnInfo(index = true) val noteId: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
