package com.salach.privatescheduler.ui.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.salach.privatescheduler.db.models.ToDoList
import com.salach.privatescheduler.repositories.ToDoListsRepository


class ToDoListsViewModel(private val repository: ToDoListsRepository) : ViewModel() {
    var toDoLists: LiveData<List<ToDoList>> = repository.allToDoLists.asLiveData()
}


class ToDoListsModelFactory(private val repository: ToDoListsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ToDoListsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ToDoListsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
