package com.salach.privatescheduler.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.ui.single_list.ListViewModel

class AddChoreDialog : DialogFragment() {
    private val listViewModel: ListViewModel by viewModels({requireParentFragment()})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_chore, null)
            builder.setView(view)
                .setPositiveButton(R.string.create, DialogInterface.OnClickListener { dialog, id ->
                    val chore = Chore(
                        id = null,
                        shortDesc = view.findViewById<TextView>(R.id.short_desc).text.toString(),
                        cron = view.findViewById<TextView>(R.id.cron).text.toString(),
                        priority = 0,
                        toDoListId = null
                    )
                    listViewModel.insertChore(chore)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}