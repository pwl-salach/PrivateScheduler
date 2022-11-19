package com.salach.privatescheduler.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.salach.privatescheduler.db.AppDatabase
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.db.models.ToDoList
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _chores = MutableLiveData<List<Chore>>()
    var chores: LiveData<List<Chore>> = AppDatabase.getInstance(getApplication()).choreDao.getAll()

    fun insertDummy(){
        viewModelScope.launch {
            val db = AppDatabase.getInstance(getApplication())
            db.toDoListDao.insertAll(
                ToDoList(null, "Dummy")
            )
            db.choreDao.insertAll(
                Chore(null, "qwe", "asd", 1, 1),
                Chore(null, "qwe", "asd", 1, 1)
            )
        }
    }
}
