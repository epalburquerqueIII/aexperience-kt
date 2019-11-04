package com.epalburquerqueiii.aexperience.UI.TiposEventos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.TiposEvento
import com.epalburquerqueiii.aexperience.Data.Model.TiposEventos
import com.epalburquerqueiii.aexperience.Data.Network.TiposEventosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TiposEventosViewModel : ViewModel() {

//    private val TiposeventosUseCase = TiposeventosUseCase()

//    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<TiposEvento>>()

    private fun setRegistros(listregistros: ArrayList<TiposEvento>) {
        registros.value = listregistros
    }

    fun getRegistros() {
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<TiposEvento>> {
        return registros
    }

    fun getRecords(): ArrayList<TiposEvento> {

        var datos = ArrayList<TiposEvento>()

        val get = RetrofitBuilder.builder().create(TiposEventosApi::class.java)
        val callget = get.List()
            callget.enqueue(object : Callback<TiposEventos> {
            override fun onFailure(call: Call<TiposEventos>, t: Throwable) {
                Log.i("Tiposeventos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<TiposEventos>, response: Response<TiposEventos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as TiposEventos
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Tiposevento(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

//    public fun Load() = changed.set(false)
//    public fun make_Change() = changed.set(true)
}


