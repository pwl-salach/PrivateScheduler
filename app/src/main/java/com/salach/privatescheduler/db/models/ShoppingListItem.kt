package com.salach.privatescheduler.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable


@DatabaseTable(tableName = "ShoppingListItems")
class ShoppingListItem {

    @DatabaseField(generatedId = true)
    var id : Int = 0

    @DatabaseField(foreign = true, foreignColumnName = "shoppingListId")
    var shoppingList : ShoppingList? = null

    @DatabaseField(foreign = true, foreignColumnName = "productId")
    var product : Product? = null

    @DatabaseField
    var quantity : Int = 0

    @DatabaseField
    var unit : String = ""
}