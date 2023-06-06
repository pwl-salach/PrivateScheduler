package com.salach.privatescheduler.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.salach.privatescheduler.R
import com.salach.privatescheduler.ui.note.NoteViewModel

class AddNotePartFragment : DialogFragment() {
    private val listViewModel: NoteViewModel by viewModels({requireParentFragment()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note_part, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val choreButton: ImageButton = view.findViewById(R.id.add_chore_btn)
        val memoButton: ImageButton = view.findViewById(R.id.add_memo_btn)
        val cancelButton: Button = view.findViewById(R.id.cancel_btn)

        choreButton.setOnClickListener {
            val additionalDialogFragment = AddChoreDialog(listViewModel)
            additionalDialogFragment.show(childFragmentManager, "additionalDialog")
        }

        memoButton.setOnClickListener {
            val anotherDialogFragment = AddMemoFragment(listViewModel)
            anotherDialogFragment.show(childFragmentManager, "anotherDialog")
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            // Customize the appearance and behavior of the dialog
            // For example, you can set a title using setTitle("Your Title")
        }
    }
}