package com.salach.privatescheduler.ui.screens.dashboard
import androidx.lifecycle.*
import com.salach.privatescheduler.db.models.Goal
import com.salach.privatescheduler.repositories.GoalsRepository


class DashboardViewModel(private val repository: GoalsRepository): ViewModel(){
    var goals: LiveData<List<Goal>> = repository.getActiveGoals().asLiveData()
}

class DashboardModelFactory(private val repository: GoalsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}