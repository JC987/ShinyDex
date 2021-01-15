package com.orangeanchorapps.shinydex.ui.search_pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ui.new_hunts.NewHuntFragment

class SearchPokemonFragment: Fragment() {
    private var bundle:Bundle = Bundle()
    lateinit var root:View
    lateinit var pb:ProgressBar
    lateinit var btnReload:Button
    lateinit var tv:TextView
    lateinit var searchPokemonViewModel: SearchPokemonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val i = bundle.getInt("search_method",0)
        val i = 2
        searchPokemonViewModel = ViewModelProvider(this).get(SearchPokemonViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_search_pokemon,container,false)
        val iv = root.findViewById<ImageView>(R.id.ivShinySprite)
        tv = root.findViewById<TextView>(R.id.tvShinyPokemonName)
        val btnAdd = root.findViewById<Button>(R.id.btnAddRandomPokemon)

        pb = root.findViewById<ProgressBar>(R.id.progressBar)
        btnReload = root.findViewById<Button>(R.id.btnReloadSearch)
        btnReload.setOnClickListener {
            btnReload.visibility = View.GONE
            randomSearch()
        }
        btnAdd.setOnClickListener {
            val b = searchPokemonViewModel.addPokemon(MainActivity.dex)
            if(b){
                Toast.makeText(root.context,"Adding hunt...",Toast.LENGTH_SHORT).show()
                goBack()
            }
            else
                Snackbar.make(it,"Please wait for pokemon to load",Snackbar.LENGTH_SHORT).show()

        }
        searchPokemonViewModel.spriteBitMap.observe(viewLifecycleOwner){
            iv.setImageBitmap(it)
            if(it!=null)
                pb.visibility = View.GONE
            else
                pb.visibility = View.VISIBLE
        }
        searchPokemonViewModel.pokemonName.observe(viewLifecycleOwner){
            tv.text = it
        }


        when (i){
            2 -> randomSearch()
            1 -> searchById()
            0 -> searchByName()

        }

        (activity as MainActivity).showBackButton()
        return root
    }

    private fun reload(){
        tv.text = getString(R.string.loading)
        btnReload.postDelayed({
            if(pb.isVisible) {
                btnReload.visibility = View.VISIBLE
                tv.text = getString(R.string.failed_to_load_pokemon)
            }
        },10000)
    }
    private fun goBack() {
        /*val fragment = NewHuntFragment()
        val manager = parentFragmentManager
        manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).addToBackStack(null).commit()*/
        findNavController().navigateUp()
    }

    private fun randomSearch() {
        reload()
        searchPokemonViewModel.fetchRandomPokemon(root.context)
    }

    private fun searchById() {
        TODO("Not yet implemented")
    }

    private fun searchByName() {
        TODO("Not yet implemented")
    }


    fun setBundle(b:Bundle) {
        bundle = b
    }

}