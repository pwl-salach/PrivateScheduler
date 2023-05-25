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
import com.salach.privatescheduler.ui.note.NoteViewModel

class AddChoreDialog(private val listId: Int) : DialogFragment() {
    private val listViewModel: NoteViewModel by viewModels({requireParentFragment()})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_chore, null)
            builder.setView(view)
                .setPositiveButton(R.string.create, DialogInterface.OnClickListener { dialog, id ->
                    val chore = Chore(
                        0,
                        view.findViewById<TextView>(R.id.short_desc).text.toString(),
                    )
//                    listViewModel.insertChore(chore)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}