package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Memo
import com.salach.privatescheduler.db.models.NotePart
import kotlinx.coroutines.flow.Flow

@Dao
interface NotePartDao {
    @Query("SELECT * FROM NotePart where noteId = :noteId")
    fun getNoteParts(noteId: Int): Flow<List<NotePart>>

    @Insert
    suspend fun insertAll(vararg notes: NotePart)


    @Query("DELETE FROM NotePart")
    suspend fun deleteAll()
}
