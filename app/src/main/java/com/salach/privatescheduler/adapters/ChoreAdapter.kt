package com.salach.privatescheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore

class ChoreAdapter(private val dataSet: Array<Chore>) : RecyclerView.Adapter<ChoreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val icon: ImageView
        val name: TextView
        val nextOccurrence: TextView

        init {
            icon = view.findViewById(R.id.img_icon)
            name = view.findViewById(R.id.text_name)
            nextOccurrence = view.findViewById(R.id.text_schedule)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chore_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon.setImageResource(R.drawable.ic_notifications_black_24dp)
        holder.name.text = dataSet[position].shortDesc
        holder.nextOccurrence.text = dataSet[position].cron
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}