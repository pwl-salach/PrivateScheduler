package com.salach.privatescheduler.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.ToDoList

@Dao
interface ToDoListDao {
    @Query("SELECT * FROM ToDoList")
    fun getAll(): LiveData<List<ToDoList>>

    @Insert
    suspend fun insertAll(vararg toDoLists: ToDoList)
}
