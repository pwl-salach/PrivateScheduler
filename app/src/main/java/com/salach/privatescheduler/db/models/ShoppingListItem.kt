package com.salach.privatescheduler.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = ShoppingList::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("shopping_list_id"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Product::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("product_id"),
        onDelete = ForeignKey.CASCADE
    )
])
data class ShoppingListItem(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "shopping_list_id", index = true) val shoppingListId: Int,
    @ColumnInfo(name = "product_id") val productId: Int,
    @ColumnInfo val quantity: Int,
    @ColumnInfo val unit: String
)
