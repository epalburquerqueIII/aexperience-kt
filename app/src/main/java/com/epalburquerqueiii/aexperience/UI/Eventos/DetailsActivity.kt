package com.epalburquerqueiii.aexperience.UI.Eventos

import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epalburquerqueiii.aexperience.Data.Model.Espacio
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Network.EventosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_espacios.*
import kotlinx.android.synthetic.main.activity_eventos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val imageurl = intent.getStringExtra("Imageurl")
        val id = intent.getStringExtra("ID")
        val descripcion = intent.getStringExtra("Descripcion")
        val year = intent.getStringExtra("Year")

        val bundle: Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Evento

        textViewTitle.text = descripcion
        textViewYear.text = "Año : " + year

        //val get = RetrofitBuilder.builder().create(EventosApi::class.java)
        //val callget = get.GetOptions()

        /*callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val Eventosarray = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        Eventosarray.add(item.DisplayText.toString())
                        if (registro.IDTiposevento == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@DetailsActivity, android.R.layout.simple_spinner_dropdown_item, Eventosarray)
                    // Set Adapter to Spinner
                    IDTipoevento!!.setAdapter(adapter)
                    IDTipoevento.setSelection(sel)
                }*/


        Picasso.get()
            .load(imageurl)
            .into(eventoThumbnail)
        }
    }