package com.salach.privatescheduler.ui.single_list

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.repositories.ChoresRepository
import kotlinx.coroutines.launch

class SingleToDoListViewModel(private val repository: ChoresRepository) : ViewModel() {
    var chores: LiveData<List<Chore>> = repository.allChores.asLiveData()

    fun insertChore(chore: Chore) = viewModelScope.launch {
        repository.insert(chore)
    }
}

class SingleToDoListModelFactory(private val repository: ChoresRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SingleToDoListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SingleToDoListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}