package com.epalburquerqueiii.aexperience.UI.Usuariosroles

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Usuariorol
import com.epalburquerqueiii.aexperience.Data.Model.Usuariosroles
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosrolesApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariosrolesViewModel : ViewModel() {

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Usuariorol>>()

    private fun setRegistros(listregistros:ArrayList<Usuariorol>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Usuariorol>> {
        return registros
    }

    fun getRecords():ArrayList<Usuariorol> {

        var datos = ArrayList<Usuariorol>()

        val get = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)
        val callget = get.List(AppData.CsrfRef)
        callget.enqueue(object : Callback<Usuariosroles> {
            override fun onFailure(call: Call<Usuariosroles>, t: Throwable) {
                Log.i("Usuariosroles Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Usuariosroles>, response: Response<Usuariosroles>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Usuariosroles
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(
            ona(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}