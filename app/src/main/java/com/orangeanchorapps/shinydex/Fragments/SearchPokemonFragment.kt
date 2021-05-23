package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangeanchorapps.shinydex.R

class SearchPokemonFragment : Fragment() {

    companion object {
        fun newInstance() = SearchPokemonFragment()
    }

    private lateinit var viewModel: SearchPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_pokemon_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchPokemonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}