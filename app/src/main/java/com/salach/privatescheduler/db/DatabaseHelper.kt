package com.salach.privatescheduler.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.salach.privatescheduler.db.models.*
import java.sql.SQLException

class DatabaseHelper(
    context: Context,
    databaseName: String,
    factor: SQLiteDatabase.CursorFactory?,
    databaseVersion: Int
) : OrmLiteSqliteOpenHelper(context, databaseName, factor, databaseVersion) {

    var choresDao : Dao<Chore, Int>? = null
        get(){
            field = genericSingletonGetter(field, Chore::class.java)
            return field
        }

    var productsDao: Dao<Product, Int>? = null
        get(){
            field = genericSingletonGetter(field, Product::class.java)
            return field
        }

    var shoppingListsDao: Dao<ShoppingList, Int>? = null
        get(){
            field = genericSingletonGetter(field, ShoppingList::class.java)
            return field
        }

    var shoppingListItemsDao: Dao<ShoppingListItem, Int>? = null
        get(){
            field = genericSingletonGetter(field, ShoppingListItem::class.java)
            return field
        }

    private fun <T>genericSingletonGetter(field: Dao<T, Int>?, ModelClass: Class<T>): Dao<T, Int>?{
        if (field == null){
            try {
                return getDao(ModelClass)
            } catch (e: SQLException){
                e.printStackTrace()
            }
        }
        return field
    }

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(connectionSource, Chore::class.java)
        TableUtils.createTable(connectionSource, Product::class.java)
        TableUtils.createTable(connectionSource, ShoppingList::class.java)
        TableUtils.createTable(connectionSource, ShoppingListItem::class.java)
        val tmpChore = Chore()
        tmpChore.shortDesc = "qweqweqwee"
        choresDao?.create(tmpChore)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}