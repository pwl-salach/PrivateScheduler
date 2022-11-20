package com.salach.privatescheduler.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore



class ChoreListAdapter : ListAdapter<Chore, ChoreListAdapter.ChoreViewHolder>(ChoreComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
        return ChoreViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(R.drawable.ic_notifications_black_24dp, current.shortDesc, current.cron)
    }

    class ChoreViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val icon: ImageView
        val name: TextView
        val nextOccurrence: TextView

        init {
            icon = view.findViewById(R.id.img_icon)
            name = view.findViewById(R.id.text_name)
            nextOccurrence = view.findViewById(R.id.text_schedule)
        }

        fun bind(iconId: Int, shortDesc: String, cron: String){
            icon.setImageResource(iconId)
            name.text = shortDesc
            nextOccurrence.text = cron
        }

        companion object {
            fun create(parent: ViewGroup): ChoreViewHolder{
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