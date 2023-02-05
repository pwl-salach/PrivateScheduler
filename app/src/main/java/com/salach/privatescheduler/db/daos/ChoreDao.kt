package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Chore
import kotlinx.coroutines.flow.Flow

@Dao
interface ChoreDao {
    @Query("SELECT * FROM Chore")
    fun getAll(): Flow<List<Chore>>

    @Query("SELECT * FROM Chore WHERE noteId = :noteId")
    fun getAllFromNote(noteId: Int): Flow<List<Chore>>

    @Insert
    suspend fun insertAll(vararg chores: Chore)

    @Query("DELETE FROM Chore")
    suspend fun deleteAll()
}
