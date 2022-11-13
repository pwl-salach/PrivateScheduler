package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.ShoppingListItem

@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM ShoppingListItem")
    fun getAll(): List<ShoppingListItem>
}