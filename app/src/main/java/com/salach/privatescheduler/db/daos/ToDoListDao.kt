package com.salach.privatescheduler.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.ToDoList

@Dao
interface ToDoListDao {
    @Query("SELECT * FROM ToDoList")
    fun getAll(): List<ToDoList>
}
