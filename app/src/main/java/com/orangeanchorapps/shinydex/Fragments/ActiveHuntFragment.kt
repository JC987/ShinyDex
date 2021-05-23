package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangeanchorapps.shinydex.R

class ActiveHuntFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveHuntFragment()
    }

    private lateinit var viewModel: ActiveHuntViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.active_hunt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveHuntViewModel::class.java)
        // TODO: Use the ViewModel
    }

}