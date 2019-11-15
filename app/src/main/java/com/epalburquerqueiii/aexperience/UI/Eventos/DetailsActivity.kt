package com.epalburquerqueiii.aexperience.UI.Eventos

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.epalburquerqueiii.aexperience.Data.Model.Config
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*



class DetailsActivity : AppCompatActivity() {

    //private var fecha : String = ""


    fun loadImageFromUrl(imageview: ImageView, imageurl: String?) {
        Log.i("Procesando la  imagen ", imageurl)
        try {
            Picasso.get()
                .load(Config.cms_url + imageurl)
                .into(imageview)

        } catch (e: Exception) {
            Log.i("Error en la imagen ", imageurl)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.epalburquerqueiii.aexperience.R.layout.activity_details)


        val bundle: Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Evento

        loadImageFromUrl(ImagenEventoThumbail, registro.Imageslide)
        textViewTitle.text = registro.Title
        textViewYear.text = registro.Date
        textViewDescription.text = registro.Body
        //      textViewTitle.text = descripcion
        //      textViewYear.text = "Año : " + year

        /*fun showDatePickerDialog() {
            val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                // +1 because January is zero
                val selectedDate = "%02d-%02d-%04d".format(day, month+1, year)
                textViewYear.setText(selectedDate)
                fecha = selectedDate
            })

            newFragment.show(supportFragmentManager, "datePicker")
        }*/

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



    }
}