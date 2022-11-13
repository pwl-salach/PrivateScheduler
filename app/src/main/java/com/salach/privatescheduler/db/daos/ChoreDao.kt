package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.privatescheduler.db.models.Chore

@Dao
interface ChoreDao {
    @Query("SELECT * FROM Chore")
    fun getAll(): List<Chore>

    @Insert
    fun insertAll(vararg chores: Chore)
}
