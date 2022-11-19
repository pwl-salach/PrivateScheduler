package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = ToDoList::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("to_do_list_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Chore(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "short_desc") val shortDesc: String,
    @ColumnInfo val cron: String,
    @ColumnInfo val priority: Int,
    @ColumnInfo(name= "to_do_list_id", index = true) val toDoListId: Int?
)
