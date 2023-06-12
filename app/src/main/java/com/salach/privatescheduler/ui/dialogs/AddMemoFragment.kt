package com.salach.privatescheduler.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.db.models.Memo
import com.salach.privatescheduler.ui.note.NoteViewModel

class AddMemoFragment(private val listViewModel: NoteViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add_memo, null)
            builder.setView(view)
                .setPositiveButton(R.string.create, DialogInterface.OnClickListener { dialog, id ->
                    val memo = Memo(
                        0,  // will be overriden anyway
                        view.findViewById<TextView>(R.id.memo_txt).text.toString(),
                    )
                    listViewModel.insertMemo(memo)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }
}