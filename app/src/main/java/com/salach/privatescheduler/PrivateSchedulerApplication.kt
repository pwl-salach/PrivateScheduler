package com.salach.privatescheduler

import android.app.Application
import com.salach.privatescheduler.db.AppDatabase
import com.salach.privatescheduler.repositories.ChoresRepository
import com.salach.privatescheduler.repositories.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PrivateSchedulerApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val notesRepository by lazy { NotesRepository(database.noteDao) }
    val choresRepository by lazy { ChoresRepository(database.choreDao) }
    val memosRepository by lazy { MemosRepository(database.memoDao) }
}
