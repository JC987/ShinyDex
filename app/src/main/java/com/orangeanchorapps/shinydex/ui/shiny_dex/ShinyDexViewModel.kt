package com.orangeanchorapps.shinydex.ui.shiny_dex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ShinyDexViewModel : ViewModel() {

    private val TAG = "ShinyDex"

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _shinyName = MutableLiveData<String>().apply {
        value = "loading..."
    }

    val shinyName: LiveData<String> = _shinyName

    private val _spriteBitMap = MutableLiveData<Bitmap>().apply {
        value = null
    }

    val spriteBitMap: LiveData<Bitmap> = _spriteBitMap

    fun getTang(){
        val client = OkHttpClient()
        // completed / already caught pokemon names will be saved to db, this is just a test
        val request: Request = Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/114")
                .build()


        client .newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle this
                Log.d(TAG, "onFailure: failed response")
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle this
                //works
                var jsonObject = JSONObject(response.body?.string())

                Log.d(TAG, "onResponse: " + " :: " + jsonObject.get("abilities"))
                var a:String = jsonObject.get("name").toString()

                var j = JSONObject(jsonObject.getString("sprites"))
                var b:String = j.get("front_shiny").toString()
                Log.d(TAG, "onResponse: " + " :: " + a)



                Log.d(TAG, "onResponse: " + " :: " + b)

                //use postvalue, it is async
                _shinyName.postValue(a)

               }
        })

    }





    fun getTangImage(){
        var client = OkHttpClient()
        //I will store image url to db and fetch kinda like this
        val request = Request.Builder()
                .url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/114.png")
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
                Log.d(TAG, "onFailure: failed to get image")
            }

            override fun onResponse(call: Call, response: Response) {
                var istream = response.body?.byteStream()
                var bitmap = BitmapFactory.decodeStream(istream)

                _spriteBitMap.postValue(bitmap)
            }

        })

    }
}