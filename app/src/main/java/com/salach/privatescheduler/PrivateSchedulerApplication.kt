package com.salach.privatescheduler

import android.app.Application
import com.salach.privatescheduler.db.AppDatabase
import com.salach.privatescheduler.repositories.ChoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PrivateSchedulerApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val repository by lazy { ChoreRepository(database.choreDao) }
}