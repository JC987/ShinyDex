package com.orangeanchorapps.shinydex.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
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
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        val textInput = view.findViewById<TextInputLayout>(R.id.editText)


        btnRan.setOnClickListener {
            findNavController().navigate(R.id.action_searchPokemonFragment_to_locatedPokemonFragment)
        }

        btnSearch.setOnClickListener {
            //textInput.editText?.text.toString().isDigitsOnly()

            if (!textInput.editText?.text.isNullOrEmpty()) {
                val b = Bundle()
                b.putString("input", textInput.editText?.text.toString())
                findNavController().navigate(R.id.action_searchPokemonFragment_to_locatedPokemonFragment, b)
            } else {
                Toast.makeText(requireContext(), "Cant be empty!", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }



}