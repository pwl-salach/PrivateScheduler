package com.salach.privatescheduler.enums

import com.salach.privatescheduler.R

enum class ListIcon(val id: Int) {
    HOME(R.drawable.ic_home_black_24dp),
    ALERT(R.drawable.ic_notifications_black_24dp),
    TIME(com.google.android.material.R.drawable.ic_clock_black_24dp);

    companion object {
        fun getValues(): List<Int> {
            return values().map { it.id }
        }
    }
}