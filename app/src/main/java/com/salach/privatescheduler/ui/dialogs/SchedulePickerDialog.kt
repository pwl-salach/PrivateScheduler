package com.salach.privatescheduler.ui.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.salach.privatescheduler.R
import com.salach.privatescheduler.enums.Period
import com.salach.privatescheduler.structures.Schedule
import com.salach.privatescheduler.ui.parts.DialogButtons

class SchedulePickerDialog(val listener: SchedulePickerDialogListener) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_schedule_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickedStartDate = view.findViewById<TextView>(R.id.picked_start_date)
        setupDatePicker(pickedStartDate)
        val pickedEndDate = view.findViewById<TextView>(R.id.picked_end_date)
        setupDatePicker(pickedEndDate)

        val pickedTime = view.findViewById<TextView>(R.id.picked_time)
        setupTimePicker(pickedTime)

        val periodEnabled = view.findViewById<Switch>(R.id.period_sw)
        val periodPickedLayout = view.findViewById<LinearLayout>(R.id.period_layout)
        configurePeriodPickerControl(periodEnabled, periodPickedLayout)

        val periodSpinner = view.findViewById<Spinner>(R.id.period_spinner)
        populatePeriodSpinnerValues(periodSpinner)

        val dialogButtons = view.findViewById<DialogButtons>(R.id.dialog_buttons)
        dialogButtons.setPositiveButtonListener{

        }
        dialogButtons.setNegativeButtonListener{ dismiss() }
    }

    private fun setupDatePicker(pickedStartDate: TextView) {
        pickedStartDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                    // Step 3: Update the TextView with the selected date
                    val formattedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear
                    )
                    pickedStartDate.text = formattedDate
                }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun setupTimePicker(pickedTime: TextView) {
        pickedTime.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val timePickerDialog =
                TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    pickedTime.text = formattedTime
                }, hour, minute, true)
            timePickerDialog.show()
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO fix hardcoded dimensions
        dialog?.window?.setLayout(800, 1000)
    }

    private fun configurePeriodPickerControl(controllingSwitch: Switch, controlledLayout: View){
        controlledLayout.visibility = View.INVISIBLE
        controllingSwitch.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){
                controlledLayout.visibility = View.VISIBLE
            } else {
                controlledLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun populatePeriodSpinnerValues(periodSpinner: Spinner){
        val enumValues = Period.values().filterNot { it == Period.NONE }

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            enumValues.map { enumConstant ->
                resources.getString(resources.getIdentifier("enum_${enumConstant.name}", "string", requireContext().packageName))
            }
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        periodSpinner.adapter = spinnerAdapter
    }
}

interface SchedulePickerDialogListener {
    fun onDataReceived(schedule: Schedule)
}
