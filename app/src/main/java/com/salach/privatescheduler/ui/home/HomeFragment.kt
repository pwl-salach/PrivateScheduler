package com.salach.privatescheduler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.salach.privatescheduler.PrivateSchedulerApplication
import com.salach.privatescheduler.databinding.FragmentHomeBinding
import com.salach.privatescheduler.db.models.Chore
import com.salach.privatescheduler.ui.adapters.ChoreListAdapter
import com.salach.privatescheduler.ui.dialogs.AddChoreDialog

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var choresList: RecyclerView? = null
    private var dummyButton: FloatingActionButton? = null
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((activity?.application as PrivateSchedulerApplication).repository)
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        choresList = binding.tableChores
        val adapter = ChoreListAdapter()
        choresList!!.adapter = adapter
        choresList!!.layoutManager = LinearLayoutManager(activity)
        homeViewModel.chores.observe(viewLifecycleOwner, Observer { chores ->
            chores.let{
                adapter.submitList(it)
            }
        })

        //
        dummyButton = binding.fab
        dummyButton!!.setOnClickListener{
            showAddChoreDialog()
        }

        return root
    }

    fun showAddChoreDialog(){
        val dialog = AddChoreDialog()
        dialog.show(childFragmentManager, "AddChoreDialog")
    }
//    private fun updateDisplayedList(chores: List<Chore>){
//        choresList?.removeAllViews()
//        for(chore in chores){
//            val row = TableRow(activity)
//            val name = TextView(activity)
//            name.text = chore.shortDesc
//            row.addView(name)
//            choresList?.addView(row)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUpcomingChores() {

    }

}