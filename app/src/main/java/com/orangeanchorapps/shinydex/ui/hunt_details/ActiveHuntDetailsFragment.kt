package com.orangeanchorapps.shinydex.ui.hunt_details

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.interfaces.Message


class ActiveHuntDetailsFragment: Fragment() {
    lateinit var c:Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_active_hunt_details, container, false)
        c = root.context
        val iv = root.findViewById<ImageView>(R.id.ivShinySprite)
        val tvName = root.findViewById<TextView>(R.id.tvShinyPokemonName)
        val activeHuntDetailsViewModel =
        ViewModelProvider(this).get(ActiveHuntDetailsViewModel::class.java)

        (activity as MainActivity).showBackButton()
        val index = (activity as Message).receiveMessage()
        val dex = MainActivity.dex
        Log.d("ShinyDex", "onCreateView: $index")

        val hunt: ShinyHunt = dex.getActiveHunts().get(index)


        val pb = root.findViewById<ProgressBar>(R.id.progressBar)


        val tvEncounter = root.findViewById<TextView>(R.id.tvEncounterActiveHunt)
        val btnInc = root.findViewById<Button>(R.id.btnAddOne)
        val btnDec = root.findViewById<Button>(R.id.btnSubOne)

        btnInc.setOnClickListener {
            activeHuntDetailsViewModel.incrementEncounters()
        }

        btnDec.setOnClickListener {
            val b = activeHuntDetailsViewModel.decrementEncounters()
            if(!b)
                Snackbar.make(it,"Can't have negative encounters",Snackbar.LENGTH_SHORT).show()

        }
        tvEncounter.setOnClickListener {
            setEncounterDialog(it,activeHuntDetailsViewModel::setEncounters)

        }

        activeHuntDetailsViewModel.encounters.observe(viewLifecycleOwner, {
            hunt.encounters = it
            activeHuntDetailsViewModel.updateShinyHunt(hunt)
        })

        activeHuntDetailsViewModel.text.observe(viewLifecycleOwner,{
            tvEncounter.text = it
        })

        activeHuntDetailsViewModel.spriteBitMap.observe(viewLifecycleOwner){
            iv.setImageBitmap(it)
            if(it != null)
                pb.visibility = View.GONE
        }

        tvName.text = hunt.pokemon.name
        //iv.setImageBitmap(hunt.pokemon.sprite)
        activeHuntDetailsViewModel.setEncounters(hunt.encounters)
        activeHuntDetailsViewModel.setPokemonId(hunt.pokemonId)
        activeHuntDetailsViewModel.loadPokemonSprite()
        return root
    }


    private fun setEncounterDialog(v:View, f: (Int)->Boolean) {
        val dialog = AlertDialog.Builder(c)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_et_number,null)
        dialog.setView(dialogLayout)
        val et = dialogLayout.findViewById<EditText>(R.id.dialog_ET_Number)
        var ans = -1
        dialog.setTitle("Set Encounters")
        dialog.setMessage("Enter the number of encounters you have:")
        dialog.setPositiveButton("Confirm"){ dialogInterface: DialogInterface, i: Int ->
            ans = et.text.toString().toInt()
            val b = f(ans)
            if(b)
                Snackbar.make(v,"Setting encounters",Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(v,"Can't have negative encounters",Snackbar.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("Cancel"){ dialogInterface: DialogInterface, i: Int ->
            Snackbar.make(v,"Cancelled",Snackbar.LENGTH_SHORT).show()
        }
        dialog.show()
    }


}








