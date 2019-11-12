package com.epalburquerqueiii.aexperience.UI.Eventos

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.Data.Model.Eventos
import com.epalburquerqueiii.aexperience.Data.Network.EventosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventosViewModel : ViewModel() {

    private val registros = MutableLiveData<ArrayList<Evento>>()

    private fun setRegistros(listregistros:ArrayList<Evento>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Evento>> {
        return registros
    }

    fun getRecords():ArrayList<Evento> {

        var datos = ArrayList<Evento>()

        val get = RetrofitBuilder.builder().create(EventosApi::class.java)
        val callget = get.GetEventos()
        callget.enqueue(object : Callback<Eventos> {
            override fun onFailure(call: Call<Eventos>, t: Throwable) {
                Log.i("Eventos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Eventos>, response: Response<Eventos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Eventos
//                val size = response.Records!!.size
                datos = response.Records!!
               setRegistros(datos)
            }
        })

        if (datos.size == 0) {
            datos!!.add(Evento(0,"No hay registros","","","","","","","","","",""))
        }
        return datos

    }

}