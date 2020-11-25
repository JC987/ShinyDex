package com.orangeanchorapps.shinydex.ui.active_hunts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orangeanchorapps.shinydex.R

class ActiveHuntsFragment : Fragment() {

    private lateinit var activeHuntsViewModel: ActiveHuntsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activeHuntsViewModel =
                ViewModelProvider(this).get(ActiveHuntsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_active_hunts, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        activeHuntsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}