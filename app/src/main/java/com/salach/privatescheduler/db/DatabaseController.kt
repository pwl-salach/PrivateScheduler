package com.salach.privatescheduler.db

import android.content.Context
import com.salach.privatescheduler.db.models.Chore

class DatabaseController(context: Context) {
    private val helper: DatabaseHelper = DatabaseHelper(context, "internal.db", null, 1)

    fun getUpcomingChores() : List<Chore>{
        return helper.choresDao?.queryBuilder()?.query() ?: emptyList()
    }
}