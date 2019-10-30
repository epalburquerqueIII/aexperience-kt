package com.epalburquerqueiii.aexperience.UI.Pagos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Pago
import com.epalburquerqueiii.aexperience.Data.Model.Pagos
import com.epalburquerqueiii.aexperience.Data.Network.PagosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagosViewModel : ViewModel() {

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Pago>>()

    private fun setRegistros(listregistros:ArrayList<Pago>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Pago>> {
        return registros
    }

    fun getRecords():ArrayList<Pago> {

        var datos = ArrayList<Pago>()

        val get = RetrofitBuilder.builder().create(PagosApi::class.java)
        val callget = get.get()
        callget.enqueue(object : Callback<Pagos> {
            override fun onFailure(call: Call<Pagos>, t: Throwable) {
                Log.i("Pagos Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Pagos>, response: Response<Pagos>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Pagos
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })

        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}