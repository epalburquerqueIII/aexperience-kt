package com.epalburquerqueiii.aexperience.UI.Horarios

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Horario
import com.epalburquerqueiii.aexperience.Data.Model.Horarios
import com.epalburquerqueiii.aexperience.Data.Network.HorariosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HorariosViewModel : ViewModel() {


//    private val HorariosUseCase = HorariosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Horario>>()

    private fun setRegistros(listregistros:ArrayList<Horario>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Horario>> {
        return registros
    }

    fun getRecords():ArrayList<Horario> {

        var datos = ArrayList<Horario>()

        val get = RetrofitBuilder.builder().create(HorariosApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Horarios> {
            override fun onFailure(call: Call<Horarios>, t: Throwable) {
                Log.i("Horarios Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Horarios>, response: Response<Horarios>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Horarios
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Horario(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}