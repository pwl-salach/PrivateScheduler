package com.salach.privatescheduler.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "ShoppingLists")
class ShoppingList {
    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var shortDesc: String = ""
}