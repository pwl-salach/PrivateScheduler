package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM ShoppingList")
    fun getAll(): Flow<List<ShoppingList>>
}