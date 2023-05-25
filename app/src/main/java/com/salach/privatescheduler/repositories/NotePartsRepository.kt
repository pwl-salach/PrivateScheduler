package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.daos.MemoDao
import com.salach.privatescheduler.db.daos.NotePartDao
import com.salach.privatescheduler.db.models.NotePart
import com.salach.privatescheduler.enums.NotePartType
import kotlinx.coroutines.flow.Flow

class NotePartsRepository(
    private val notePartDao: NotePartDao,
    private val memoDao: MemoDao,
    private val choreDao: ChoreDao
) {
    fun getAllParts(noteId: Int): Flow<List<NotePart>>{
        return notePartDao.getNoteParts(noteId)
    }

    suspend fun<T> getFullRepresentation(partId: Int, type: NotePartType): T? {
        if (type == NotePartType.MEMO ){
            @Suppress("UNCHECKED_CAST")
            return memoDao.getById(partId) as T
        } else if (type == NotePartType.CHORE) {
            @Suppress("UNCHECKED_CAST")
            return choreDao.getById(partId) as T
        }
        return null
    }
}