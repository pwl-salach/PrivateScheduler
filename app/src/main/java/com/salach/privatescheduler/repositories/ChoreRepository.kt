package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.models.Chore
import kotlinx.coroutines.flow.Flow

class ChoreRepository(private val choreDao: ChoreDao) {
    val allChores: Flow<List<Chore>> = choreDao.getAll()

    suspend fun insert(vararg chore: Chore){
        choreDao.insertAll(*chore)
    }
}