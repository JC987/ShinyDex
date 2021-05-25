package com.orangeanchorapps.shinydex.Fragments

import android.graphics.ImageDecoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.orangeanchorapps.shinydex.R

class LocatedPokemonFragment : Fragment() {


    private lateinit var viewModel: LocatedPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.located_pokemon_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LocatedPokemonViewModel::class.java)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvName = view.findViewById<TextView>(R.id.tvPokemonName)
        val pb = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel.pokemonName.observe(viewLifecycleOwner, {
            tvName.text = it
        })
        viewModel.bitmap.observe(viewLifecycleOwner, {
            if(it != null)
                pb.visibility = View.GONE
            imageView.setImageBitmap(it)
        })

        return view
    }



}