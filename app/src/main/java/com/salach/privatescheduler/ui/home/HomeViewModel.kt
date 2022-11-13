package com.salach.privatescheduler.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salach.privatescheduler.db.AppDatabase
import com.salach.privatescheduler.db.models.Chore

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

//    private val _chores = MutableLiveData<List<Chore>>().apply {
//        value = AppDatabase.getInstance().choreDao.getAll()
//    }
    val chores: LiveData<List<Chore>> = AppDatabase.getInstance().choreDao.getAll()
}
