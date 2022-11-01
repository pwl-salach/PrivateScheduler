package com.salach.privatescheduler.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.salach.privatescheduler.db.models.Chore
import java.sql.SQLException

class DatabaseHelper(
    context: Context,
    databaseName: String,
    factor: SQLiteDatabase.CursorFactory?,
    databaseVersion: Int
) : OrmLiteSqliteOpenHelper(context, databaseName, factor, databaseVersion) {

    var choreDao : Dao<Chore?, Int?>? = null
        get(){
            if (null == field) {
                try {
                    field = getDao(Chore::class.java)
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
            return field
        }

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(connectionSource, Chore::class.java)
        val tmpChore = Chore()
        tmpChore.shortDesc = "qweqweqwee"
        choreDao?.create(tmpChore)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}