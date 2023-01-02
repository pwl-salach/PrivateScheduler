package com.salach.privatescheduler.ui.popups

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.PopupWindow
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.salach.privatescheduler.R
import com.salach.privatescheduler.ui.lists.ToDoListsAdapter
import com.salach.privatescheduler.ui.utils.OnItemClickListener


class IconPickerPopup(context: Context?, private val anchor: View, icons: List<Int>) {
    private val popupWindow: PopupWindow
    private val adapter: IconPickerAdapter

    init {
        val inflater = LayoutInflater.from(context)
        val iconPickerView = inflater.inflate(R.layout.popup_icon_picker, null)

        popupWindow = PopupWindow(
            iconPickerView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Set the background and dismiss listener for the popup window
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.LTGRAY))
        popupWindow.setOutsideTouchable(true)
        // not sure if needed
        // popupWindow.setOnDismissListener {
            // Do something when the popup window is dismissed
        // }

        // Set up the RecyclerView for displaying the icons
        val recyclerView = iconPickerView.findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(context, 4)
        recyclerView.layoutManager = layoutManager
        adapter = IconPickerAdapter(icons)
        recyclerView.adapter = adapter
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.adapter.setOnItemClickListener(listener)
    }

    fun show() {
        popupWindow.showAsDropDown(anchor)
    }

    fun dismiss() {
        popupWindow.dismiss()
    }
}

class IconPickerAdapter(private val icons: List<Int>): ListAdapter<Int, IconPickerAdapter.IconViewHolder>(IconComparator()){
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        view.setOnClickListener {  }
        return IconViewHolder(view)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val iconId = icons[position]
        holder.icon.setImageResource(iconId)
        if(listener != null){
            holder.icon.setOnClickListener{
                listener!!.onItemClick(iconId)
            }
        }
    }

    class IconViewHolder(view: View) : ViewHolder(view){
        val icon = view.findViewById<ImageView>(R.id.img_icon)
    }

    class IconComparator: DiffUtil.ItemCallback<Int>(){
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
}
