package com.salach.privatescheduler.ui.parts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.salach.privatescheduler.R

class DialogButtons(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var positiveButton: Button
    private var negativeButton: Button

    fun setPositiveButtonListener(listener: OnClickListener) {
        positiveButton.setOnClickListener(listener)
        showPositiveButton()
    }

    fun setNegativeButtonListener(listener: OnClickListener) {
        negativeButton.setOnClickListener(listener)
        showNegativeButton()
    }
    
    init {
        LayoutInflater.from(context).inflate(R.layout.dialog_buttons_layout, this, true)
        negativeButton = findViewById(R.id.negative_btn)
        positiveButton = findViewById(R.id.positive_btn)
        hideNegativeButton()
        hidePositiveButton()
    }

    private fun showPositiveButton() {
        positiveButton.visibility = View.VISIBLE
    }

    private fun hidePositiveButton() {
        positiveButton.visibility = View.GONE
    }

    private fun showNegativeButton() {
        negativeButton.visibility = View.VISIBLE
    }

    private fun hideNegativeButton() {
        negativeButton.visibility = View.GONE
    }
}