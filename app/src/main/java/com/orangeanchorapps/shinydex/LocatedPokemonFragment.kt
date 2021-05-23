package com.orangeanchorapps.shinydex

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LocatedPokemonFragment : Fragment() {

    companion object {
        fun newInstance() = LocatedPokemonFragment()
    }

    private lateinit var viewModel: LocatedPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.located_pokemon_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocatedPokemonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}