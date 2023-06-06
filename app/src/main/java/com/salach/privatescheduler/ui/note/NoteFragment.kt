package com.salach.privatescheduler.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.databinding.FragmentSingleToDoListBinding
import com.salach.privatescheduler.ui.dialogs.AddNotePartFragment

class NoteFragment : Fragment() {

    private var _binding: FragmentSingleToDoListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listId = 0
    private var viewModel: NoteViewModel? = null
    private var partsList: RecyclerView? = null
    private var addNotePartFab: FloatingActionButton? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleToDoListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadArguments()
        val adapter = NoteAdapter((activity?.application as PrivateSchedulerApplication).notePartsRepository)
        setupViewModel(adapter)
        setupRecycleView(adapter)
        setupFab()
        return root
    }

    private fun loadArguments() {
        listId = arguments?.getInt("listId")!!
    }

    private fun setupViewModel(adapter: NoteAdapter) {
        viewModel = ViewModelProvider(
            this,
            NoteModelFactory(
                (activity?.application as PrivateSchedulerApplication).notePartsRepository,
                listId
            )
        ).get(NoteViewModel::class.java)
        viewModel!!.parts.observe(viewLifecycleOwner, Observer { part ->
            print(part)
            part.let {
                adapter.submitList(it)
            }
        })
    }

    private fun setupRecycleView(adapter: NoteAdapter) {
        partsList = binding.tableChores
        partsList!!.adapter = adapter
        partsList!!.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupFab() {
        addNotePartFab = binding.fabAddNotePart
        addNotePartFab!!.setOnClickListener {
            showAddNotePartDialog()
        }
    }

    private fun showAddNotePartDialog(){
        val dialog = AddNotePartFragment()
        dialog.show(childFragmentManager, "AddChoreDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}