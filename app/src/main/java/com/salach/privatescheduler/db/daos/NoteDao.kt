package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): Flow<List<Note>>

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()
}
