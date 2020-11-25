package com.orangeanchorapps.shinydex.ui.new_hunts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orangeanchorapps.shinydex.R

class NewHuntFragment : Fragment() {

    private lateinit var newHuntViewModel: NewHuntViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newHuntViewModel =
                ViewModelProvider(this).get(NewHuntViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_hunt, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        newHuntViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}