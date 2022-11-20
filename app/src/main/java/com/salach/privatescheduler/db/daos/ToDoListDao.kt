package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.ToDoList
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Query("SELECT * FROM ToDoList")
    fun getAll(): Flow<List<ToDoList>>

    @Insert
    suspend fun insertAll(vararg toDoLists: ToDoList)

    @Query("DELETE FROM ToDoList")
    suspend fun deleteAll()
}
