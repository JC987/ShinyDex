package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.ActiveHuntViewModel

class ActiveHuntFragment : Fragment() {



    private lateinit var viewModel: ActiveHuntViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.active_hunt_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ActiveHuntViewModel::class.java)

        val rv = view.findViewById<RecyclerView>(R.id.rvActive)
        val adapter = ListAdapter(findNavController())
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(view.context)

        viewModel.activeShinyHunts.observe(viewLifecycleOwner,{
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })



        return view
    }



}