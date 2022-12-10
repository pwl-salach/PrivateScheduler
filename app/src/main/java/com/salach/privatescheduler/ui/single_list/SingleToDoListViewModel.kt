package com.salach.privatescheduler.ui.single_list

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.repositories.ChoreRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ChoreRepository) : ViewModel() {
    var chores: LiveData<List<Chore>> = repository.allChores.asLiveData()

    fun insertChore(chore: Chore) = viewModelScope.launch {
        repository.insert(chore)
    }
}

class ToDoListModelFactory(private val repository: ChoreRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}