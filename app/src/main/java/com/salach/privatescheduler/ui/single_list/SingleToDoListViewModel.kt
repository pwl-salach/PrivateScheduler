package com.salach.privatescheduler.ui.single_list

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.repositories.ChoresRepository
import kotlinx.coroutines.launch

class SingleToDoListViewModel(private val repository: ChoresRepository, private val listId: Int) : ViewModel() {
    var chores: LiveData<List<Chore>> = repository.getChildChores(listId).asLiveData()

    fun insertChore(chore: Chore) = viewModelScope.launch {
        repository.insert(chore)
    }
}

class SingleToDoListModelFactory(private val repository: ChoresRepository, private val listId: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SingleToDoListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SingleToDoListViewModel(repository, listId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}