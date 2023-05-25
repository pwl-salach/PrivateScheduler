package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.models.Chore
import kotlinx.coroutines.flow.Flow

class ChoresRepository(private val choreDao: ChoreDao) {
    val allChores: Flow<List<Chore>> = choreDao.getAll()

//    fun getChildChores(parentId: Int): Flow<List<Chore>>{
//        return choreDao.getAllFromNote(parentId)
//    }

    suspend fun insert(vararg chore: Chore){
        choreDao.insertAll(*chore)
    }
}