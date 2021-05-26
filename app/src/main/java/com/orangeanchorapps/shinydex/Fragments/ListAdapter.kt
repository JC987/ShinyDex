package com.orangeanchorapps.shinydex.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.orangeanchorapps.shinydex.Classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.R

class ListAdapter(findNavController: NavController) : RecyclerView.Adapter<ListAdapter.CustomViewHolder>() {
    val navController = findNavController
    private var allActive: List<PokemonShinyHunt> = emptyList()

    fun setData(list :List<PokemonShinyHunt>){
        allActive = list
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(pokemonShinyHunt: PokemonShinyHunt, navController: NavController){
            val tmp = pokemonShinyHunt.pokemonName
            itemView.findViewById<TextView>(R.id.tvItemName).text = tmp
            itemView.findViewById<TextView>(R.id.tvItemEncounters).text = pokemonShinyHunt.encounters.toString()

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", pokemonShinyHunt.id)
                bundle.putString("name", pokemonShinyHunt.pokemonName)
                bundle.putInt("pokemonId", pokemonShinyHunt.pokemonId)
                bundle.putInt("encounters", pokemonShinyHunt.encounters)
                navController.navigate(R.id.action_activeHuntFragment_to_activeHuntDetailedFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(allActive[position], navController )
    }

    override fun getItemCount(): Int {
        return allActive.size
    }
}