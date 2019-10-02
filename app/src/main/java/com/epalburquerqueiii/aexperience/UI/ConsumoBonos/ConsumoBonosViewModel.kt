package com.epalburquerqueiii.aexperience.UI.ConsumoBonos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBono
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBonos
import com.epalburquerqueiii.aexperience.Data.Network.ConsumoBonosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumoBonosViewModel : ViewModel() {


//    private val ConsumoBonosUseCase = ConsumoBonosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<ConsumoBono>>()

    private fun setRegistros(listregistros:ArrayList<ConsumoBono>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<ConsumoBono>> {
        return registros
    }

    fun getRecords():ArrayList<ConsumoBono> {

        var datos = ArrayList<ConsumoBono>()

        val get = RetrofitBuilder.builder().create(ConsumoBonosApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<ConsumoBonos> {
            override fun onFailure(call: Call<ConsumoBonos>, t: Throwable) {
                Log.i("ConsumoBonos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<ConsumoBonos>, response: Response<ConsumoBonos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as ConsumoBonos
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(ConsumoBono(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    public fun Load()=changed.set(false)
    public fun make_Change()=changed.set(true)

}