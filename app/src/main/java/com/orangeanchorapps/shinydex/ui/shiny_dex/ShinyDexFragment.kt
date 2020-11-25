package com.orangeanchorapps.shinydex.ui.shiny_dex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orangeanchorapps.shinydex.R

class ShinyDexFragment : Fragment() {

    private lateinit var shinyDexViewModel: ShinyDexViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shinyDexViewModel =
                ViewModelProvider(this).get(ShinyDexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shiny_dex, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        shinyDexViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}