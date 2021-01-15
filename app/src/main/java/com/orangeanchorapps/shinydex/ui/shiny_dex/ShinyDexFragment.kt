package com.orangeanchorapps.shinydex.ui.shiny_dex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.interfaces.Message
import com.orangeanchorapps.shinydex.ui.shiny_details.ShinyPokemonDetailFragment


class ShinyDexFragment : Fragment() {

    private lateinit var shinyDexViewModel: ShinyDexViewModel
    private val TAG = "ShinyDex"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shinyDexViewModel =
                ViewModelProvider(this).get(ShinyDexViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shiny_dex, container, false)

        val listView: ListView = root.findViewById(R.id.shinyDexListView)


        var adapter = ArrayAdapter<String>(root.context,R.layout.layout_item, MainActivity.dex.getCompletedHuntsNames())
        listView.adapter = adapter

        shinyDexViewModel.completedHunts.observe(viewLifecycleOwner){
            Log.d(TAG, "completedHunts: observe")
            MainActivity.dex.reset(it)

            adapter = ArrayAdapter<String>(root.context,R.layout.layout_item, MainActivity.dex.getCompletedHuntsNames())
            listView.adapter = adapter
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            findNavController().navigate(R.id.action_navigation_shiny_dex_to_navigation_shiny_pokemon)
            val message = (activity as Message)
            message.setMessage(i)
            /*val fragment = ShinyPokemonDetailFragment()
            val manager = parentFragmentManager
            val message = (activity as Message)
            message.setMessage(i)
            manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).addToBackStack("shiny_pokemon_detail").commit()
            */
        }


        (activity as MainActivity).hideBackButton()

        return root
    }
}