package com.salach.privatescheduler.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.privatescheduler.db.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>
}