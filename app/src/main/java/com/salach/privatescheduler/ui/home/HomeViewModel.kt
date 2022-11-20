package com.salach.privatescheduler.ui.home

import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.repositories.ChoreRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ChoreRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var chores: LiveData<List<Chore>> = repository.allChores.asLiveData()

    fun insertDummy(){
        viewModelScope.launch {
            repository.insert(
                Chore(null, "qwe", "asd", 1, 1)
            )
        }
    }
}

class HomeViewModelFactory(private val repository: ChoreRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}