package com.salach.privatescheduler.ui.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.ToDoList


class ToDoListsAdapter : ListAdapter<ToDoList, ToDoListsAdapter.ToDoListViewHolder>(ToDoListComparator()) {
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, R.drawable.ic_notifications_black_24dp, current.name)
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
                edit.setOnClickListener({
                })
            }
        }

        companion object {
            fun create(parent: ViewGroup) : ToDoListViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_list_row, parent, false)
                return ToDoListViewHolder(view)
            }
        }
    }

    class ToDoListComparator : DiffUtil.ItemCallback<ToDoList>(){
        override fun areItemsTheSame(oldItem: ToDoList, newItem: ToDoList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ToDoList, newItem: ToDoList): Boolean {
            return oldItem.id == newItem.id
        }
    }

}