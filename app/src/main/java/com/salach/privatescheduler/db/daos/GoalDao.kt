package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
//    @Query("SELECT * FROM NotePart where noteId = :noteId")
//    fun getNoteParts(noteId: Int): Flow<List<Goal>>
    @Query("SELECT * FROM Goal")
    fun getAll(): Flow<List<Goal>>

    @Insert
    suspend fun insert(note: Goal): Long

    @Insert
    suspend fun insertAll(vararg note: Goal): List<Long>

    @Query("DELETE FROM Goal")
    suspend fun deleteAll()
}
