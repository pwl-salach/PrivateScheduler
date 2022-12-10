package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ToDoListDao
import com.salach.privatescheduler.db.models.ToDoList
import kotlinx.coroutines.flow.Flow

class ToDoListsRepository(private val toDoListDao: ToDoListDao) {
    val allToDoLists: Flow<List<ToDoList>> = toDoListDao.getAll()
}