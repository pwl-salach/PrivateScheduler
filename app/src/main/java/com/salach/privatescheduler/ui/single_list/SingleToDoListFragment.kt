package com.salach.privatescheduler.ui.single_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.databinding.FragmentSingleToDoListBinding
import com.salach.privatescheduler.ui.dialogs.AddChoreDialog

class SingleToDoListFragment : Fragment() {

    private var _binding: FragmentSingleToDoListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listId = 0
    private var choresList: RecyclerView? = null
    private var viewModel: SingleToDoListViewModel? = null
    private var dummyButton: FloatingActionButton? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSingleToDoListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        choresList = binding.tableChores
        val adapter = SingleToDoListAdapter()
        choresList!!.adapter = adapter
        choresList!!.layoutManager = LinearLayoutManager(activity)

        listId = arguments?.getInt("listId")!!

        viewModel = ViewModelProvider(this,
            SingleToDoListModelFactory((activity?.application as PrivateSchedulerApplication).choresRepository,
                listId
            )
        ).get(SingleToDoListViewModel::class.java)
        viewModel!!.chores.observe(viewLifecycleOwner, Observer { chores ->
            chores.let{
                adapter.submitList(it)
            }
        })

        dummyButton = binding.fab
        dummyButton!!.setOnClickListener{
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