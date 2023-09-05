package com.salach.privatescheduler.ui.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.repositories.GoalsRepository
import com.salach.privatescheduler.ui.components.CreateNewOverlay
import com.salach.privatescheduler.ui.components.CurrentGoals
import com.salach.privatescheduler.ui.screens.notes_list.ToDoListsModelFactory
import com.salach.privatescheduler.ui.screens.notes_list.ToDoListsViewModel

class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels {
        DashboardModelFactory((activity?.application as PrivateSchedulerApplication).goalsRepository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {
                CurrentGoals(viewModel.goals)
            }
        }
    }
}