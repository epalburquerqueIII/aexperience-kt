package com.epalburquerqueiii.aexperience.UI.Tipospagos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.Data.Model.Personas
import com.epalburquerqueiii.aexperience.Data.Model.Tipospago
import com.epalburquerqueiii.aexperience.Data.Model.Tipospagos
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.TipospagosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipospagosViewModel : ViewModel() {


//    private val personasUseCase = PersonasUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Tipospago>>()

    private fun setRegistros(listregistros:ArrayList<Tipospago>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Tipospago>> {
        return registros
    }

    fun getRecords():ArrayList<Tipospago> {

        var datos = ArrayList<Tipospago>()

        val get = RetrofitBuilder.builder().create(TipospagosApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Tipospagos> {
            override fun onFailure(call: Call<Tipospagos>, t: Throwable) {
                Log.i("Tipospagos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Tipospagos>, response: Response<Tipospagos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Tipospagos
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Tipospago(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    public fun Load()=changed.set(false)
    public fun make_Change()=changed.set(true)

}