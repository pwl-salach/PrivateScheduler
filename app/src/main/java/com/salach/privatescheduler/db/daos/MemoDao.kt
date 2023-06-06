package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Memo
import kotlinx.coroutines.flow.Flow


@Dao
interface MemoDao {
    @Query("SELECT * FROM Memo")
    fun getAll(): Flow<List<Memo>>

    @Query("SELECT * FROM Memo WHERE id = :id")
    suspend fun getById(id: Long): Memo

    @Insert
    suspend fun insertAll(vararg notes: Memo)

    @Query("DELETE FROM Memo")
    suspend fun deleteAll()
}
