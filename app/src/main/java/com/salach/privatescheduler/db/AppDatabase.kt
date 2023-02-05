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
        Note::class
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
    abstract val noteDao: NoteDao

    private class DevSetupCallback(private val scope: CoroutineScope) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { appDatabase ->
                scope.launch {
                    appDatabase.choreDao.deleteAll()
                    appDatabase.noteDao.deleteAll()

                    appDatabase.noteDao.insertAll(
                        Note("Generic", ListIcon.HOME.id, 0, 1),
                        Note("Initial", ListIcon.HOME.id, 0, 2),
                        Note("Test", ListIcon.ALERT.id, 0, 3)
                    )
                    appDatabase.choreDao.insertAll(
                        Chore(1, "QWE"),
                        Chore(1, "ASD"),
                        Chore(2, "ZXC"),
                        Chore(3, "RTY"),
                    )
                }
            }
        }
    }
}
