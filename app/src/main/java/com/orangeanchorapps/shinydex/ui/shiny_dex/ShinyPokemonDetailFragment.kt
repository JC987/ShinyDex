package com.orangeanchorapps.shinydex.ui.shiny_dex


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

class ShinyPokemonDetailFragment:Fragment() {
    private val TAG = "ShinyDex"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val shinyDexViewModel = ViewModelProvider(requireParentFragment()).get(ShinyDexViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shiny_pokemon_details, container, false)
        val name = root.findViewById<TextView>(R.id.tvShinyPokemonName)
        val sprite = root.findViewById<ImageView>(R.id.ivShinySprite)
        //val back = root.findViewById<Button>(R.id.btnBackShinyDetails)
        val pb = root.findViewById<ProgressBar>(R.id.progressBar)
        name.text = shinyDexViewModel.shinyName.value
        shinyDexViewModel.shinyName.observe(viewLifecycleOwner, {
            name.text = it
        })

        shinyDexViewModel.spriteBitMap.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: sprite uri observed")
            if (it != null)
                pb.visibility = View.GONE
            sprite.setImageURI(null)
            sprite.setImageBitmap(it)
        })

        shinyDexViewModel.getTang()
        shinyDexViewModel.getTangImage()


        (activity as MainActivity).showBackButton()

        return root

    }
}