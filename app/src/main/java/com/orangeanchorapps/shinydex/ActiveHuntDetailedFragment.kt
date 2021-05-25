package com.orangeanchorapps.shinydex

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ActiveHuntDetailedFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveHuntDetailedFragment()
    }

    private lateinit var viewModel: ActiveHuntDetailedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.active_hunt_detailed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveHuntDetailedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}