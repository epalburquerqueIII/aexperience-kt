package com.epalburquerqueiii.aexperience.UI.Bonos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Bono
import com.epalburquerqueiii.aexperience.Data.Model.Bonos
import com.epalburquerqueiii.aexperience.Data.Network.BonosApi

import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BonosViewModel : ViewModel() {


//    private val BonosUseCase = BonosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Bono>>()

    private fun setRegistros(listregistros:ArrayList<Bono>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Bono>> {
        return registros
    }

    fun getRecords():ArrayList<Bono> {

        var datos = ArrayList<Bono>()

        val get = RetrofitBuilder.builder().create(BonosApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Bonos> {
            override fun onFailure(call: Call<Bonos>, t: Throwable) {
                Log.i("Bonos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Bonos>, response: Response<Bonos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Bonos
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Bonos(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}