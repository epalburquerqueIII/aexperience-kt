package com.epalburquerqueiii.aexperience.UI.Reservas

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Reserva
import com.epalburquerqueiii.aexperience.Data.Model.Reservas
import com.epalburquerqueiii.aexperience.Data.Network.ReservasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservasViewModel : ViewModel() {


//    private val AutorizadosUseCase = AutorizadosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Reserva>>()

    private fun setRegistros(listregistros:ArrayList<Reserva>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Reserva>> {
        return registros
    }

    fun getRecords():ArrayList<Reserva> {

        var datos = ArrayList<Reserva>()

        val get = RetrofitBuilder.builder().create(ReservasApi::class.java)
        val callget = get.List(AppData.CsrfRef)
        callget.enqueue(object : Callback<Reservas> {
            override fun onFailure(call: Call<Reservas>, t: Throwable) {
                Log.i("Reservas Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Reservas>, response: Response<Reservas>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Reservas
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })

        //if (datos.size == 0) {
            //datos!!.add(Reserva(0,"No hay registros","",0,0,0,0))
        //}

        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}