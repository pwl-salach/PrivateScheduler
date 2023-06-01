package com.salach.privatescheduler.ui.note

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.NotePart
import com.salach.privatescheduler.repositories.NotePartsRepository

class NoteViewModel(private val repository: NotePartsRepository, private val listId: Int) : ViewModel() {
    var parts: LiveData<List<NotePart>> = repository.getAllParts(listId).asLiveData()

//    fun insertChore(chore: Chore) = viewModelScope.launch {
//        repository.insert(chore)
//    }
}

class NoteModelFactory(private val repository: NotePartsRepository, private val listId: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository, listId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}