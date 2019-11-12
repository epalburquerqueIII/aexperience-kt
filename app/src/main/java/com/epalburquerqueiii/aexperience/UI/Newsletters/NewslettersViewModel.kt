package com.epalburquerqueiii.aexperience.UI.Newsletters

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Newsletter
import com.epalburquerqueiii.aexperience.Data.Model.Newsletters
import com.epalburquerqueiii.aexperience.Data.Network.NewslettersApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewslettersViewModel : ViewModel() {


//    private val MenusUseCase = MenusUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Newsletter>>()

    private fun setRegistros(listregistros:ArrayList<Newsletter>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Newsletter>> {
        return registros
    }

    fun getRecords():ArrayList<Newsletter> {

        var datos = ArrayList<Newsletter>()

        val get = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        val callget = get.List(AppData.CsrfRef)
        callget.enqueue(object : Callback<Newsletters> {
            override fun onFailure(call: Call<Newsletters>, t: Throwable) {
                Log.i("Newsletters Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Newsletters>, response: Response<Newsletters>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Newsletters
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}