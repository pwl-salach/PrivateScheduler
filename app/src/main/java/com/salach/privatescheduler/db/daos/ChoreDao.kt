package com.salach.privatescheduler.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.Chore

@Dao
interface ChoreDao {
    @Query("SELECT * FROM Chore")
    fun getAll(): LiveData<List<Chore>>

}
