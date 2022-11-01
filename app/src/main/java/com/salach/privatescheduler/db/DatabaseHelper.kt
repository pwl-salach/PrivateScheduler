package com.salach.privatescheduler.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.salach.privatescheduler.db.models.*
import java.sql.SQLException
import kotlin.reflect.full.memberProperties
import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.javaField

class DatabaseHelper(
    context: Context,
    databaseName: String,
    factor: SQLiteDatabase.CursorFactory?,
    databaseVersion: Int
) : OrmLiteSqliteOpenHelper(context, databaseName, factor, databaseVersion) {

    var choreDao : Dao<Chore, Int>? = null
        get(){
            return genericGetter("choreDao", Chore::class.java)
        }

    var productsDao: Dao<Product, Int>? = null
        get(){
            return genericGetter("productsDao", Product::class.java)
        }


    private fun <T> genericGetter(field: String, ModelClass: Class<T>) : Dao<T, Int> {
        // this is a bit slow
        val property = this::class.memberProperties.find { it.name == field }
        // avoids calling getter recursively
        var dao = property?.javaField?.get(this)
        if (dao == null){
            try {
                dao = getDao(ModelClass)
            } catch (e: SQLException){
                e.printStackTrace()
            }
            if (property is KMutableProperty<*>) {
                property.setter.call(this, dao)

            }
        }
        return dao as Dao<T, Int>
    }

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(connectionSource, Chore::class.java)
        TableUtils.createTable(connectionSource, Product::class.java)
        TableUtils.createTable(connectionSource, ShoppingList::class.java)
        TableUtils.createTable(connectionSource, ShoppingListItem::class.java)
        val tmpChore = Chore()
        tmpChore.shortDesc = "qweqweqwee"
        choreDao?.create(tmpChore)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}