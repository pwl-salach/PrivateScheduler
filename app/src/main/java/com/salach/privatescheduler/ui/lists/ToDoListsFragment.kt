package com.salach.privatescheduler.ui.lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salach.privatescheduler.R
import com.salach.privatescheduler.databinding.FragmentSingleToDoListBinding
import com.salach.privatescheduler.databinding.FragmentToDoListsBinding

class ToDoListsFragment : Fragment() {

    private var _binding: FragmentToDoListsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ToDoListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoListsBinding.inflate(inflater, container, false)
        return binding.root
    }
}