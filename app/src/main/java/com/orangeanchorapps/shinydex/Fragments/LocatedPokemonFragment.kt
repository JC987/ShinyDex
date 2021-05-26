package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.LocatedPokemonViewModel
import kotlinx.coroutines.runBlocking

class LocatedPokemonFragment : Fragment() {


    private lateinit var viewModel: LocatedPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.located_pokemon_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LocatedPokemonViewModel::class.java)
        val btn = view.findViewById<Button>(R.id.btnStartNew)
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

        btn.setOnClickListener {
            Snackbar.make(view,"Adding new Shiny Hunt!",Snackbar.LENGTH_SHORT).show()
            runBlocking {
                val job = viewModel.addToDatabase()
                job.join()
            }

            findNavController().navigateUp()
        }

        return view
    }



}