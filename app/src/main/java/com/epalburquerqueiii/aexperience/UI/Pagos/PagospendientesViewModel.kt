package com.epalburquerqueiii.aexperience.UI.Pagos

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Pagopendiente
import com.epalburquerqueiii.aexperience.Data.Model.Pagospendientes
import com.epalburquerqueiii.aexperience.Data.Network.PagospendientesApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagospendientesViewModel : ViewModel() {

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Pagopendiente>>()

    private fun setRegistros(listregistros:ArrayList<Pagopendiente>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Pagopendiente>> {
        return registros
    }

    fun getRecords():ArrayList<Pagopendiente> {

        var datos = ArrayList<Pagopendiente>()

        val get = RetrofitBuilder.builder().create(PagospendientesApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Pagospendientes> {
            override fun onFailure(call: Call<Pagospendientes>, t: Throwable) {
                Log.i("Pagopendiente Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Pagospendientes>, response: Response<Pagospendientes>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Pagospendientes
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })

        return datos
    }

    public fun Load()=changed.set(false)
    public fun make_Change()=changed.set(true)

}