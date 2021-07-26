package com.orangeanchorapps.shinydex.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ViewModels.ActiveHuntDetailedViewModel

class ActiveHuntDetailedFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveHuntDetailedFragment()
    }

    private lateinit var viewModel: ActiveHuntDetailedViewModel

    lateinit var c:Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.active_hunt_detailed_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ActiveHuntDetailedViewModel::class.java)
        c = view.context
        val bundle = arguments

        val id = requireArguments().getInt("id")
        val pokemonId = requireArguments().getInt("pokemonId")
        val name = requireArguments().getString("name")
        val encounters = requireArguments().getInt("encounters")
        val tvName = view.findViewById<TextView>(R.id.tvPokemonName)
        val tvEncounters = view.findViewById<TextView>(R.id.tvEncounters)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val pb = view.findViewById<ProgressBar>(R.id.progressBar)
        val btnAddOne = view.findViewById<Button>(R.id.btnAddOne)
        val btnSubOne = view.findViewById<Button>(R.id.btnSubOne)
        val btnRefresh = view.findViewById<Button>(R.id.btnRefresh)
        val btnFinish = view.findViewById<Button>(R.id.btnFinishShinyHunt)

        tvName.text = name
        val temp = "Encounters: $encounters"
        tvEncounters.text =  temp

        // when the id is zero it is an empty shiny hunt and has not been loaded
        if (viewModel.shinyHunt.value?.id == 0) {
            val shinyHunt = ShinyHunt(id = id, pokemonId = pokemonId, encounters = encounters)
            viewModel.setShinyHunt(shinyHunt)
        }

        viewModel.encounters.observe(viewLifecycleOwner, {
            val temp = "Encounters: $it"
            tvEncounters.text = temp
        })
        viewModel.bitmap.observe(viewLifecycleOwner, {
            if(it != null) {
                imageView.setImageBitmap(it)
                pb.visibility = View.GONE
            }
        })
        viewModel.errorLoadingSprite.observe(viewLifecycleOwner) {
            if (it == true) {
                pb.visibility = View.GONE
                imageView.setImageBitmap(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)?.toBitmap())
                Toast.makeText(requireContext(), "Error Loading Image...", Toast.LENGTH_SHORT).show()
                btnRefresh.visibility = View.VISIBLE
            }
        }

        btnFinish.setOnClickListener {
            val dialog = AlertDialog.Builder(c).run {
                setTitle("Finish this shiny hunt?")
                setMessage("Did you catch a shiny pokemon?")
                setPositiveButton("I did!", DialogInterface.OnClickListener { dialogInterface, i ->
                    Snackbar.make(view,"Congratulations!! Finishing the shiny hunt...", Snackbar.LENGTH_SHORT).show()
                    viewModel.finishShinyHuny()
                    findNavController().navigateUp()
                })
                setNegativeButton("Not yet!") { _, _ -> }
                show()
            }


        }

        btnAddOne.setOnClickListener {
            viewModel.addOne()
        }
        btnSubOne.setOnClickListener {
            viewModel.subOne()
        }
        btnRefresh.setOnClickListener {
            pb.visibility = View.VISIBLE
            imageView.setImageBitmap(null)
            btnRefresh.visibility = View.GONE

            val shinyHunt = ShinyHunt(id = id, pokemonId = pokemonId, encounters = encounters)
            viewModel.setShinyHunt(shinyHunt)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        //Toast.makeText(c, "test", Toast.LENGTH_SHORT).show()

    }
}