package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.daos.MemoDao
import com.salach.privatescheduler.db.daos.NoteDao
import com.salach.privatescheduler.db.models.Memo
import com.salach.privatescheduler.db.models.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAll()

    suspend fun insert(vararg list: Note) {
        noteDao.insertAll(*list)
    }
}