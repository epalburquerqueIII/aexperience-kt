package com.epalburquerqueiii.aexperience.UI.Autorizados

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Autorizado
import com.epalburquerqueiii.aexperience.Data.Model.Autorizados
import com.epalburquerqueiii.aexperience.Data.Network.AutorizadosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutorizadosViewModel : ViewModel() {


//    private val AutorizadosUseCase = AutorizadosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Autorizado>>()

    private fun setRegistros(listregistros:ArrayList<Autorizado>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Autorizado>> {
        return registros
    }

    fun getRecords():ArrayList<Autorizado> {

        var datos = ArrayList<Autorizado>()

        val get = RetrofitBuilder.builder().create(AutorizadosApi::class.java)
        val callget = get.List(AppData.CsrfRef)

        callget.enqueue(object : Callback<Autorizados> {
            override fun onFailure(call: Call<Autorizados>, t: Throwable) {
                Log.i("Autorizados Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Autorizados>, response: Response<Autorizados>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Autorizados
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Autorizado(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}