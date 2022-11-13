package com.salach.privatescheduler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.salach.privatescheduler.databinding.FragmentHomeBinding
import com.salach.privatescheduler.db.models.Chore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var choresList: TableLayout? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        choresList = binding.tableChores
//        updateDisplayedList(homeViewModel.chores)

        return root
    }

//    private fun updateDisplayedList(chores: LiveData<List<Chore>>){
//        for(chore in chores.value!!){
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