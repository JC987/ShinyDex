package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangeanchorapps.shinydex.R

class CompletedHuntFragment : Fragment() {

    companion object {
        fun newInstance() = CompletedHuntFragment()
    }

    private lateinit var viewModel: CompletedHuntViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.completed_hunt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CompletedHuntViewModel::class.java)
        // TODO: Use the ViewModel
    }

}