package com.orangeanchorapps.shinydex.ui.active_hunts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.interfaces.Message
import com.orangeanchorapps.shinydex.ui.hunt_details.ActiveHuntDetailsFragment

class ActiveHuntsFragment : Fragment() {

    private lateinit var activeHuntsViewModel: ActiveHuntsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activeHuntsViewModel =
                ViewModelProvider(this).get(ActiveHuntsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_active_hunts, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        val listView: ListView = root.findViewById(R.id.activeHuntListView)
        activeHuntsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        var adapter = ArrayAdapter<String>(root.context, R.layout.layout_item, MainActivity.dex.getActiveHuntsName())

        listView.adapter = adapter

        activeHuntsViewModel.completedHunts.observe(viewLifecycleOwner){
            MainActivity.dex.reset(it)
            adapter = ArrayAdapter<String>(root.context, R.layout.layout_item, MainActivity.dex.getActiveHuntsName())

            listView.adapter = adapter
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            findNavController().navigate(R.id.action_navigation_active_hunts_to_navigation_active_hunt_details)
            val m: Message = activity as Message
            m.setMessage(i)
            /*val fragment = ActiveHuntDetailsFragment()
            val manager = parentFragmentManager
            val m: Message = activity as Message
            m.setMessage(i)
            manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).addToBackStack("active_hunt_details").commit()

             */
        }


        (activity as MainActivity).hideBackButton()
        return root
    }
}