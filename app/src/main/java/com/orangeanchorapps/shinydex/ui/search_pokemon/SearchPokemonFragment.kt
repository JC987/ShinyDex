package com.orangeanchorapps.shinydex.ui.search_pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orangeanchorapps.shinydex.R

class SearchPokemonFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_active_hunts,container,false)
        return root
    }

}