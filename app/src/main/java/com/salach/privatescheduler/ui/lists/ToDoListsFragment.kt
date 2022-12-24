package com.salach.privatescheduler.ui.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.R
import com.salach.privatescheduler.databinding.FragmentToDoListsBinding


class ToDoListsFragment : Fragment() {

    private var _binding: FragmentToDoListsBinding? = null
    private val binding get() = _binding!!

    private var toDoLists: RecyclerView? = null
    private val viewModel: ToDoListsViewModel by viewModels {
        ToDoListsModelFactory((activity?.application as PrivateSchedulerApplication).toDoListsRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoListsBinding.inflate(inflater, container, false)

        toDoLists = binding.toDoLists
        val adapter = ToDoListsAdapter()
        adapter.setOnItemClickListener(object: ToDoListsAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                val args = Bundle()
                args.putInt("listId", id)
                findNavController().navigate(R.id.action_navigation_lists_to_single_to_do_list_fragment, args)
            }
        })
        toDoLists!!.adapter = adapter
        toDoLists!!.layoutManager = LinearLayoutManager(activity)
        viewModel.toDoLists.observe(viewLifecycleOwner) { toDoLists ->
            toDoLists.let {
                adapter.submitList(it)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}