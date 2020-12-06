package com.orangeanchorapps.shinydex.ui.hunt_details

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon


class ActiveHuntDetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_active_hunt_details, container, false)
        val iv = root.findViewById<ImageView>(R.id.ivSpriteActiveHunt)
        val otherPath: Uri = Uri.parse("android.resource://com.orangeanchorapps.shinydex/drawable/shiny_squirtle_api.png")
        val imageUri = "drawable://" + R.drawable.shiny_squirtle_api
        val d = BitmapDrawable.createFromPath(otherPath.toString())
        val b = BitmapFactory.decodeResource(resources,R.drawable.shiny_squirtle_api)

        val p = Pokemon("name", b)

        iv.setImageBitmap(p.sprite)
        return root
    }
}








