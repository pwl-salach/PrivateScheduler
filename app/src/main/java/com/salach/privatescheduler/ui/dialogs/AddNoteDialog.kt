package com.salach.privatescheduler.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Note
import com.salach.privatescheduler.ui.notes_list.ToDoListsViewModel


class AddNoteDialog() : DialogFragment() {
    private val listViewModel: ToDoListsViewModel by viewModels({requireParentFragment()})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_list, null)
            builder.setView(view).setPositiveButton("Create", DialogInterface.OnClickListener { dialog, id ->
                    val list = Note(
                        name = "",
                        iconColor = 333,
                        iconShape = R.drawable.ic_home_black_24dp
                    )
                    listViewModel.insertList(list)
                }
            )
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}