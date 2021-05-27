package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.CompletedHuntDetailedViewModel

class CompletedHuntDetailedFragment : Fragment() {



    private lateinit var viewModel: CompletedHuntDetailedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.completed_hunt_detailed_fragment, container, false)
        viewModel = ViewModelProvider(this).get(CompletedHuntDetailedViewModel::class.java)
        val name = requireArguments().getString("name")
        val pokemonId = requireArguments().getInt("pokemonId")
        val encounters = requireArguments().getInt("encounters")

        val tvName = view.findViewById<TextView>(R.id.tvPokemonName)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val pb = view.findViewById<ProgressBar>(R.id.progressBar)

        val tvEncounters = view.findViewById<TextView>(R.id.tvCompletedEncounters)

        tvName.text = name
        val tmp = "Captured in $encounters encounters!"
        tvEncounters.text = tmp

        viewModel.loadImage(pokemonId)

        viewModel.bitmap.observe(viewLifecycleOwner){
            if(it != null){
                pb.visibility = View.GONE
                imageView.setImageBitmap(it)
            }
        }

        return view
    }



}