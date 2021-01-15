package com.orangeanchorapps.shinydex.ui.shiny_details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.interfaces.Message


class ShinyPokemonDetailFragment:Fragment() {
    private val TAG = "ShinyDex"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val shinyPokemonDetailViewModel = ViewModelProvider(requireParentFragment()).get(ShinyPokemonDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shiny_pokemon_details, container, false)
        val name = root.findViewById<TextView>(R.id.tvShinyPokemonName)
        val sprite = root.findViewById<ImageView>(R.id.ivShinySprite)
        val pb = root.findViewById<ProgressBar>(R.id.progressBar)

        val index = (activity as Message).receiveMessage()
        val hunt = MainActivity.dex.getHunt(index)
        val encounters = root.findViewById<TextView>(R.id.tvEncountersShinyDetails)
        val tmp = "${getString(R.string.total_encounters)} ${hunt.encounters}"
        encounters.text = tmp

        shinyPokemonDetailViewModel.pokemonName.observe(viewLifecycleOwner, {
            name.text = it
        })
        shinyPokemonDetailViewModel.resetSprite()
        shinyPokemonDetailViewModel.spriteBitMap.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: sprite uri observed")
            if (it != null) {
                pb.visibility = View.GONE
                name.text = hunt.pokemon.name
            }
            sprite.setImageBitmap(it)

        })
        shinyPokemonDetailViewModel.setPokemonId(hunt.pokemonId)
        shinyPokemonDetailViewModel.loadPokemonSprite()


        (activity as MainActivity).showBackButton()

        return root

    }
}