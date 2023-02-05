package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.NoteDao
import com.salach.privatescheduler.db.models.Note
import kotlinx.coroutines.flow.Flow

class ToDoListsRepository(private val noteDao: NoteDao) {
    val allToDoLists: Flow<List<Note>> = noteDao.getAll()

    suspend fun insert(vararg list: Note){
        noteDao.insertAll(*list)
    }
}