package com.salach.privatescheduler.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.salach.privatescheduler.db.daos.ChoreDao
import com.salach.privatescheduler.db.daos.ToDoListDao
import com.salach.privatescheduler.db.models.*


@Database(
    entities = [
        Chore::class,
//        Product::class,
//        ShoppingList::class,
//        ShoppingListItem::class,
        ToDoList::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context? = null): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: initializeInstance(context).also { instance = it }
            }
        }

        private fun initializeInstance(context: Context?): AppDatabase{
            return Room.databaseBuilder(
                context!!.applicationContext,
                AppDatabase::class.java, "internal.db"
            ).build()
        }
    }

    abstract val choreDao: ChoreDao
    abstract val toDoListDao: ToDoListDao
}
