package com.salach.privatescheduler.db

import android.database.sqlite.SQLiteDatabase
import java.io.File

class DatabaseController(private val databaseFile: File) {
    private lateinit var database: SQLiteDatabase

    private fun initializeSQLCipher() {
        System.loadLibrary("sqlcipher");
        database = SQLiteDatabase.openOrCreateDatabase(databaseFile,null)
    }

}