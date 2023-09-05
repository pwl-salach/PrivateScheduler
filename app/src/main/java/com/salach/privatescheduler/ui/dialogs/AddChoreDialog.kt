package com.salach.privatescheduler.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.salach.privatescheduler.R
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.structures.Schedule
import com.salach.privatescheduler.ui.screens.note.NoteViewModel
import com.salach.privatescheduler.ui.components.DialogButtons

class AddChoreDialog(private val listViewModel: NoteViewModel) : DialogFragment(), SchedulePickerDialogListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_chore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cronText = view.findViewById<Button>(R.id.add_schedule_btn)
        cronText.setOnClickListener {
            val additionalDialogFragment = SchedulePickerDialog(this)
            additionalDialogFragment.show(childFragmentManager, "schedulePicker")
        }

        val dialogButtons = view.findViewById<DialogButtons>(R.id.dialog_buttons)
        dialogButtons.setPositiveButtonListener{
            val chore = Chore(
            0,
            view.findViewById<TextView>(R.id.short_desc_txt).text.toString(),
            )
            listViewModel.insertChore(chore)
        }
        dialogButtons.setNegativeButtonListener { dismiss() }
    }

    override fun onDataReceived(schedule: Schedule) {
        println(schedule)
    }
}