package com.salach.privatescheduler.ui.note

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.db.models.Memo
import com.salach.privatescheduler.db.models.NotePart
import com.salach.privatescheduler.enums.NotePartType
import com.salach.privatescheduler.repositories.NotePartsRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NotePartsRepository, private val listId: Int) : ViewModel() {
    var parts: LiveData<List<NotePart>> = repository.getAllParts(listId).asLiveData()

    fun insertChore(chore: Chore) = viewModelScope.launch {
        repository.insert(listId, chore, NotePartType.CHORE)
    }
    fun insertMemo(memo: Memo) = viewModelScope.launch {
        repository.insert(listId, memo, NotePartType.MEMO)
    }
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