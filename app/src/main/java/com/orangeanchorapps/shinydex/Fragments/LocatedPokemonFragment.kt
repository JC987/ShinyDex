package com.orangeanchorapps.shinydex.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
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
        val btnRefresh = view.findViewById<Button>(R.id.btnRefresh)

        viewModel.pokemonName.observe(viewLifecycleOwner, {
            tvName.text = it
        })
        viewModel.bitmap.observe(viewLifecycleOwner, {
            if(it != null)
                pb.visibility = View.GONE
            imageView.setImageBitmap(it)
        })

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            if (it) {
                pb.visibility = View.GONE
                btnRefresh.visibility = View.VISIBLE
                imageView.setImageBitmap(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)?.toBitmap())
                tvName.text = "Error loading Pokemon"
                Toast.makeText(requireContext(), "Error loading Pokemon", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.pokemonPair.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.usePair(it)
            }
        }
        btnRefresh.setOnClickListener {
            imageView.setImageBitmap(null)
            tvName.text = "Loading..."
            pb.visibility = View.VISIBLE
            btnRefresh.visibility = View.GONE
            viewModel.loadPokemon()
        }

        btn.setOnClickListener {
            Snackbar.make(view,"Adding new Shiny Hunt!",Snackbar.LENGTH_SHORT).show()
            runBlocking {
                val job = viewModel.addToDatabase()
                job.join()
            }

            findNavController().navigateUp()
        }

        val input:String? = arguments?.getString("input")

        if (input.isNullOrEmpty()) {
            viewModel.loadRandom()
        } else {
            viewModel.loadInput(input)
        }

        return view
    }



}