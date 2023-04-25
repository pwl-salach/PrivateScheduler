package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.salach.privatescheduler.enums.NotePartType


@Entity(
    foreignKeys = [ForeignKey(
        entity = Note::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("noteId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class NotePart(
    @ColumnInfo(index = true) val noteId: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val type: NotePartType,
    @ColumnInfo val position: Int? = null,
)
