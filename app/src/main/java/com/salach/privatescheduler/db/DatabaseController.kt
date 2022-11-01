package com.salach.privatescheduler.db

import android.content.Context
import android.util.Log
import com.salach.privatescheduler.db.models.Chore

class DatabaseController(context: Context) {
    private val helper: DatabaseHelper = DatabaseHelper(context, "internal.db", null, 1)

    fun getAnyData() : List<Chore>{
        val x: List<Chore> = helper.choreDao?.queryForAll()?.filterNotNull() ?: emptyList()
        for (i: Chore in x){
                Log.d("Data", i.shortDesc)
        }
        return x
    }
}