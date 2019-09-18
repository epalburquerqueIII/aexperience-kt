package com.epalburquerqueiii.aexperience.UI.Personas

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.Data.Model.Personas
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonasViewModel : ViewModel() {


//    private val personasUseCase = PersonasUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Persona>>()

    private fun setRegistros(listregistros:ArrayList<Persona>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Persona>> {
        return registros
    }

    fun getRecords():ArrayList<Persona> {

        var datos = ArrayList<Persona>()

        val get = RetrofitBuilder.builder().create(PersonasApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Personas> {
            override fun onFailure(call: Call<Personas>, t: Throwable) {
                Log.i("Personas Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Personas>, response: Response<Personas>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Personas
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Persona(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    public fun Load()=changed.set(false)
    public fun make_Change()=changed.set(true)

}