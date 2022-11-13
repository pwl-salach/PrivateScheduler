package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.ShoppingList

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM ShoppingList")
    fun getAll(): List<ShoppingList>
}