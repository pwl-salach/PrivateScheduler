package com.salach.privatescheduler.ui.notes_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.R
import com.salach.privatescheduler.databinding.FragmentToDoListsBinding
import com.salach.privatescheduler.enums.ListIcon
import com.salach.privatescheduler.ui.popups.IconPickerPopup
import com.salach.privatescheduler.ui.utils.OnItemClickListener


class NotesListFragment : Fragment() {

    private var _binding: FragmentToDoListsBinding? = null
    private val binding get() = _binding!!

    private var toDoLists: RecyclerView? = null
    private val viewModel: ToDoListsViewModel by viewModels {
        ToDoListsModelFactory((activity?.application as PrivateSchedulerApplication).toDoListsRepository)
    }
    private var addListFAB: FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoListsBinding.inflate(inflater, container, false)

        toDoLists = binding.toDoLists
        val adapter = NotesListAdapter()
        adapter.setOnItemClickListener(object: NotesListAdapter.OnItemClickListener{
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
                adapter.updateData(it)
            }
        }
        val searchBar = binding.inputSearchBar
        searchBar.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(sequence)
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        val markerFilterButton = binding.btnMarkerFilter
        markerFilterButton.setOnClickListener {
            if(context != null){
                val popup = IconPickerPopup(
                    context,
                    markerFilterButton,
                    ListIcon.getValues() + R.drawable.ic_invisible
                )
                popup.setOnItemClickListener(object: OnItemClickListener{
                    override fun onItemClick(id: Int) {
                        if(id == R.drawable.ic_invisible){
                            adapter.getIconFilter().filter(null)
                        } else {
                            adapter.getIconFilter().filter(id.toString())
                        }
                        popup.dismiss()
                    }
                })
                popup.show()
            }
        }

        addListFAB = binding.fabAddList
        addListFAB!!.setOnClickListener{

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}