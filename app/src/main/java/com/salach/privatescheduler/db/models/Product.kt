package com.salach.privatescheduler.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "Products")
class Product {
    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var shortDesc: String = ""

//    @DatabaseField(foreign = true)
//    var category: ProductCategory
}