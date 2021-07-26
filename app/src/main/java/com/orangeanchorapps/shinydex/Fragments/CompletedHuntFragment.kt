package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.CompletedHuntViewModel

class CompletedHuntFragment : Fragment() {


    private lateinit var viewModel: CompletedHuntViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.completed_hunt_fragment, container, false)
        viewModel = ViewModelProvider(this).get(CompletedHuntViewModel::class.java)

        val rv = view.findViewById<RecyclerView>(R.id.rvCompleted)
        val adapter = ListAdapter(findNavController = findNavController())
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(view.context)

        viewModel.getAllCompleted.observe(viewLifecycleOwner){
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }

        rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        return view
    }


}