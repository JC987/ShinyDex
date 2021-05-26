package com.orangeanchorapps.shinydex.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.SearchPokemonViewModel

class SearchPokemonFragment : Fragment() {


    private lateinit var viewModel: SearchPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_pokemon_fragment, container, false)

        val btnRan = view.findViewById<Button>(R.id.btnSearchRandom)

        btnRan.setOnClickListener {

            findNavController().navigate(R.id.action_searchPokemonFragment_to_locatedPokemonFragment)
        }

        return view
    }



}