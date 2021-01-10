package com.orangeanchorapps.shinydex.ui.new_hunts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ui.search_pokemon.SearchPokemonFragment

class NewHuntFragment : Fragment() {

    private lateinit var newHuntViewModel: NewHuntViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newHuntViewModel =
                ViewModelProvider(this).get(NewHuntViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_hunt, container, false)

        val btn = root.findViewById<Button>(R.id.btnSearchRandomPokemon)
        btn.setOnClickListener {
            gotoSearchFragment()
        }
        newHuntViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        (activity as MainActivity).hideBackButton()
        return root
    }

    private fun gotoSearchFragment() {
        findNavController().navigate(R.id.action_navigation_new_hunt_to_navigation_search_pokemon)

/*        val fragment = SearchPokemonFragment()
        val manager = parentFragmentManager
        val bundle:Bundle = Bundle()
        bundle.putInt("search_method",2)
        fragment.setBundle(bundle)
        manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).addToBackStack("search_pokemon").commit()
        */
    }
}