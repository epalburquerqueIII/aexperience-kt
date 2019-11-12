package com.epalburquerqueiii.aexperience.UI.Espacios

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Espacio
import com.epalburquerqueiii.aexperience.Data.Model.Espacios
import com.epalburquerqueiii.aexperience.Data.Network.EspaciosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EspaciosViewModel : ViewModel() {


//    private val EspaciosUseCase = EspaciosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Espacio>>()

    private fun setRegistros(listregistros:ArrayList<Espacio>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Espacio>> {
        return registros
    }

    fun getRecords():ArrayList<Espacio> {

        var datos = ArrayList<Espacio>()

        val get = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        val callget = get.List(AppData.CsrfRef)
        callget.enqueue(object : Callback<Espacios> {
            override fun onFailure(call: Call<Espacios>, t: Throwable) {
                Log.i("Espacios Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Espacios>, response: Response<Espacios>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Espacios
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })

        if (datos.size == 0) {
            datos.add(Espacio(0,"No hay registros",0,0,0,0,"",0,0))
        }

        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}