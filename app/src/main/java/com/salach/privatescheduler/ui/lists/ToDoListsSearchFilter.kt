package com.salach.privatescheduler.ui.lists

import android.widget.Filter
import com.salach.privatescheduler.db.models.ToDoList

class ToDoListsSearchFilter(private val adapter: ToDoListsAdapter) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = FilterResults()
        adapter.filteredData = if(constraint.isNullOrEmpty()) {
            adapter.getData()
        } else {
            adapter.getData().filter { it.name.contains(constraint, ignoreCase = true) }
        }
        results.values = adapter.filteredData
        return results
    }

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredData = results?.values as List<ToDoList> ?: emptyList()
    }
}
