package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.daos.MemoDao
import com.salach.privatescheduler.db.daos.NotePartDao
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.db.models.Memo
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

    suspend fun<T> getFullRepresentation(partId: Long, type: NotePartType): T? {
        if (type == NotePartType.MEMO ){
            @Suppress("UNCHECKED_CAST")
            return memoDao.getById(partId) as T
        } else if (type == NotePartType.CHORE) {
            @Suppress("UNCHECKED_CAST")
            return choreDao.getById(partId) as T
        }
        return null
    }

    suspend fun<T> insert(noteId: Int, obj: T, type: NotePartType){
        val newPart = NotePart(noteId, type)
        val partId = notePartDao.insert(newPart)
        when (type) {
            NotePartType.MEMO -> {
                val newMemo = obj as Memo
                newMemo.id = partId
                memoDao.insertAll(newMemo)
            }
            NotePartType.CHORE -> {
                val newMemo = obj as Chore
                newMemo.id = partId
                choreDao.insertAll(newMemo)
            }
        }
    }
}