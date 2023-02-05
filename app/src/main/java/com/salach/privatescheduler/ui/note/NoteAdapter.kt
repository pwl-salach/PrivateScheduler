package com.salach.privatescheduler.ui.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore



class NoteAdapter : ListAdapter<Chore, NoteAdapter.ChoreViewHolder>(ChoreComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
        return ChoreViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.done, current.shortDesc)
    }

    class ChoreViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val done: CheckBox
        val name: TextView
        val nextOccurrence: TextView

        init {
            done = view.findViewById(R.id.chb_done)
            name = view.findViewById(R.id.txt_name)
            nextOccurrence = view.findViewById(R.id.txt_schedule)
        }

        fun bind(isDone: Boolean, shortDesc: String){
            done.isChecked = isDone
            name.text = shortDesc
        }

        companion object {
            fun create(parent: ViewGroup) : ChoreViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.chore_row, parent, false)
                return ChoreViewHolder(view)
            }
        }
    }

    class ChoreComparator : DiffUtil.ItemCallback<Chore>(){
        override fun areItemsTheSame(oldItem: Chore, newItem: Chore): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chore, newItem: Chore): Boolean {
            return oldItem.id == newItem.id
        }
    }
}