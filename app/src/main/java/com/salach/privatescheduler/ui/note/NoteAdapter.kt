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
import com.salach.privatescheduler.db.models.Memo
import com.salach.privatescheduler.db.models.NotePart
import com.salach.privatescheduler.enums.NotePartType
import com.salach.privatescheduler.repositories.NotePartsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NoteAdapter(private val notePartsRepository: NotePartsRepository) : ListAdapter<NotePart, RecyclerView.ViewHolder>(NotePartComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> MemoViewHolder.create(parent)
            2 -> ChoreViewHolder.create(parent)
            else -> MemoViewHolder.create(parent)
        }
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type){
            NotePartType.MEMO -> 1
            NotePartType.CHORE -> 2
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        GlobalScope.launch(Dispatchers.Main) {
            when (holder) {
                is ChoreViewHolder -> current.id?.let {
                    notePartsRepository.getFullRepresentation<Chore>(it, NotePartType.CHORE)
                        ?.let { holder.bind(it) }
                }
                is MemoViewHolder -> current.id?.let {
                    notePartsRepository.getFullRepresentation<Memo>(it, NotePartType.MEMO)
                        ?.let { holder.bind(it) }
                }
            }
        }
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

        fun bind(chore: Chore){
            done.isChecked = chore.done
            name.text = chore.shortDesc
        }

        companion object {
            fun create(parent: ViewGroup) : ChoreViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.chore_row, parent, false)
                return ChoreViewHolder(view)
            }
        }
    }

    class MemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView

        init {
            text = view.findViewById(R.id.txt_text)
        }

        fun bind(memo: Memo){
            text.text = memo.text
        }

        companion object{
            fun create(parent: ViewGroup) : MemoViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.memo, parent, false)
                return MemoViewHolder(view)
            }
        }
    }

    class NotePartComparator : DiffUtil.ItemCallback<NotePart>(){
        override fun areItemsTheSame(oldItem: NotePart, newItem: NotePart): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NotePart, newItem: NotePart): Boolean {
            return oldItem.id == newItem.id
        }
    }
}