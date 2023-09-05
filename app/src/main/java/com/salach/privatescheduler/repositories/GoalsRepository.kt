package com.salach.privatescheduler.repositories

import com.salach.privatescheduler.db.daos.GoalDao
import com.salach.privatescheduler.db.models.Goal
import kotlinx.coroutines.flow.Flow

class GoalsRepository(private val goalDao: GoalDao) {
    fun getActiveGoals(): Flow<List<Goal>> {
        return goalDao.getAll()
    }
}