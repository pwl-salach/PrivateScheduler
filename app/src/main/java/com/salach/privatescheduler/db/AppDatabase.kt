package com.salach.privatescheduler.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.salach.privatescheduler.db.daos.*
import com.salach.privatescheduler.db.models.*
import com.salach.privatescheduler.enums.ListIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [
        Chore::class,
        Product::class,
        ShoppingList::class,
        ShoppingListItem::class,
        ToDoList::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: initializeInstance(context, scope).also { INSTANCE = it }
            }
        }

        private fun initializeInstance(context: Context, scope: CoroutineScope): AppDatabase{
            var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "internal.db"
                )
            // check if dev env
            instance = instance.addCallback(DevSetupCallback(scope))
            return instance.build()
        }
    }

    abstract val choreDao: ChoreDao
    abstract val productDao: ProductDao
    abstract val shoppingList: ShoppingListDao
    abstract val shoppingListItemDao: ShoppingListItemDao
    abstract val toDoListDao: ToDoListDao

    private class DevSetupCallback(private val scope: CoroutineScope) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { appDatabase ->
                scope.launch {
                    appDatabase.choreDao.deleteAll()
                    appDatabase.toDoListDao.deleteAll()

                    appDatabase.toDoListDao.insertAll(
                        ToDoList(0, "Generic", ListIcon.HOME.id, 0),
                        ToDoList(1, "Initial", ListIcon.HOME.id, 0),
                        ToDoList(2, "Test", ListIcon.ALERT.id, 0)
                    )
                    appDatabase.choreDao.insertAll(
                        Chore(null, "QWE", "* * * * *", 1, null),
                        Chore(null, "ASD", "", 1, 1),
                        Chore(null, "ZXC", "* * * * 1", 1, 1),
                    )
                }
            }
        }
    }
}
