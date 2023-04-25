package com.salach.privatescheduler.ui.notes_list

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Note
import com.salach.privatescheduler.repositories.NotesRepository
import kotlinx.coroutines.launch


class ToDoListsViewModel(private val repository: NotesRepository) : ViewModel() {
    val toDoLists: LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun insertList(list: Note) = viewModelScope.launch {
        repository.insert(list)
    }
}

class ToDoListsModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ToDoListsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ToDoListsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
