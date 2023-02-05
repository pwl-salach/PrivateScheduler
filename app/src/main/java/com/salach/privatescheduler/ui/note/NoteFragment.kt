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
import com.salach.privatescheduler.ui.dialogs.AddChoreDialog

class NoteFragment : Fragment() {

    private var _binding: FragmentSingleToDoListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listId = 0
    private var choresList: RecyclerView? = null
    private var viewModel: NoteViewModel? = null
    private var addChoreFAB: FloatingActionButton? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSingleToDoListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        choresList = binding.tableChores
        val adapter = NoteAdapter()
        choresList!!.adapter = adapter
        choresList!!.layoutManager = LinearLayoutManager(activity)

        listId = arguments?.getInt("listId")!!

        viewModel = ViewModelProvider(this,
            NoteModelFactory((activity?.application as PrivateSchedulerApplication).choresRepository,
                listId
            )
        ).get(NoteViewModel::class.java)
        viewModel!!.chores.observe(viewLifecycleOwner, Observer { chores ->
            chores.let{
                adapter.submitList(it)
            }
        })

        addChoreFAB = binding.fabAddChore
        addChoreFAB!!.setOnClickListener{
            showAddChoreDialog()
        }

        return root
    }

    private fun showAddChoreDialog(){
        val dialog = AddChoreDialog(listId)
        dialog.show(childFragmentManager, "AddChoreDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}