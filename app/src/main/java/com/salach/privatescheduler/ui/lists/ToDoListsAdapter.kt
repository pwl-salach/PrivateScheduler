package com.salach.privatescheduler.ui.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.ToDoList


class ToDoListsAdapter : ListAdapter<ToDoList, ToDoListsAdapter.ToDoListViewHolder>(ToDoListComparator()),
    Filterable {
    private var listener: OnItemClickListener? = null

    private var data : List<ToDoList> = emptyList()
    private var filteredData: List<ToDoList> = emptyList()
    private lateinit var searchFilter: Filter
    private lateinit var iconFilter: Filter

    // --- Required to enable filtering ---
    override fun getItemCount(): Int {
        return filteredData.size
    }

    override fun getItem(position: Int): ToDoList {
        return filteredData[position]
    }

    fun updateData(toDoList: List<ToDoList>){
        data = toDoList
        filteredData = toDoList
        submitList(toDoList)
    }

    override fun getFilter(): Filter {
        if(!::searchFilter.isInitialized){
            searchFilter = ToDoListsSearchFilter()
        }
        return searchFilter
    }

    fun getIconFilter(): Filter {
        if(!::iconFilter.isInitialized){
            iconFilter = ToDoListsIconFilter()
        }
        return iconFilter
    }
    // === Required to enable filtering ===

    // --- Opening of child view ---
    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
    // === Opening of child view ===


    // --- Rows rendering ---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        val current = filteredData[position]
        holder.bind(current.id, current.iconShape, current.name)
        if(current.id != null && listener != null){
            holder.itemView.setOnClickListener{
                listener!!.onItemClick(current.id)
            }
        }
    }

    class ToDoListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val icon: ImageView
        val name: TextView
        val edit: Button

        init {
            icon = view.findViewById(R.id.img_icon)
            name = view.findViewById(R.id.txt_name)
            edit = view.findViewById(R.id.btn_edit)
        }

        fun bind(id: Int?, iconId: Int, shortDesc: String){
            icon.setImageResource(iconId)
            name.text = shortDesc
            if ( id != null){
                //
                edit.setOnClickListener({})
            }
        }

        companion object {
            fun create(parent: ViewGroup) : ToDoListViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_list_row, parent, false)
                return ToDoListViewHolder(view)
            }
        }
    }

    class ToDoListComparator : DiffUtil.ItemCallback<ToDoList>() {
        override fun areItemsTheSame(oldItem: ToDoList, newItem: ToDoList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ToDoList, newItem: ToDoList): Boolean {
            return oldItem.id == newItem.id
        }
    }

    // === Rows rendering ===

    // --- Rows filtering ---
    inner class ToDoListsSearchFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            filteredData = if(constraint.isNullOrEmpty()) {
                data
            } else {
                data.filter { it.name.contains(constraint, ignoreCase = true) }
            }
            results.values = filteredData
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredData = results?.values as List<ToDoList> ?: emptyList()
            notifyDataSetChanged()
        }
    }

    inner class ToDoListsIconFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            filteredData = if(constraint.isNullOrEmpty()){
                data
            } else {
                data.filter { it.iconShape == constraint.toString().toInt() }
            }
            results.values = filteredData
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredData = results?.values as List<ToDoList> ?: emptyList()
            notifyDataSetChanged()
        }
    }
    // === Rows filtering ===
}