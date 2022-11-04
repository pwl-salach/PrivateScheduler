package com.salach.privatescheduler.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "Chores")
class Chore {

    @DatabaseField(generatedId = true)
    var id: Int = 0

//    @DatabaseField(foreign = true, foreignAutoRefresh = true)
//    var collectionId : Int = 0
    @DatabaseField
    var shortDesc: String = ""

    @DatabaseField
    var cron: String = ""

    @DatabaseField
    var priority: Int = 0
}
