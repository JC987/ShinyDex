package com.orangeanchorapps.shinydex.ui.shiny_dex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.ui.new_hunts.NewHuntFragment
import okhttp3.*
import okhttp3.internal.wait
import org.json.JSONObject
import org.json.JSONStringer
import java.io.IOException


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

        var adapter = ArrayAdapter<String>(root.context,R.layout.support_simple_spinner_dropdown_item, listOf("Tangela","other","third"))
        //val btn: Button = root.findViewById(R.id.testBtn)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val fragment = ShinyPokemonDetailFragment()
            val manager = parentFragmentManager

            manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit()

        }



        /*shinyDexViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/


        //shinyDexViewModel.getTang()
        return root
    }
}