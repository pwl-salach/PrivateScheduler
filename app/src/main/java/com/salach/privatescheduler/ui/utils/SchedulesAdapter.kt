package com.salach.privatescheduler.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.structures.Schedule

class SchedulesAdapter : ListAdapter<Schedule, SchedulesAdapter.SchedulesViewHolder>(SchedulesComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    class SchedulesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        companion object {
            fun create(parent: ViewGroup) : SchedulesViewHolder {
                val view = LayoutInflater.from(parent.context).inflate()
            }
        }
    }

    class SchedulesComparator : DiffUtil.ItemCallback<Schedule>(){
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            TODO("Not yet implemented")
        }

    }

}