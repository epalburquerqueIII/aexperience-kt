package com.epalburquerqueiii.aexperience.UI.Usuarios

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Usuario
import com.epalburquerqueiii.aexperience.Data.Model.Usuarios
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariosViewModel : ViewModel() {


//    private val UsuariosUseCase = UsuariosUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Usuario>>()

    private fun setRegistros(listregistros:ArrayList<Usuario>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Usuario>> {
        return registros
    }

    fun getRecords():ArrayList<Usuario> {

        var datos = ArrayList<Usuario>()

        val get = RetrofitBuilder.builder().create(UsuariosApi::class.java)
        val callget = get.get()
        callget.enqueue(object : Callback<Usuarios> {
            override fun onFailure(call: Call<Usuarios>, t: Throwable) {
                Log.i("Usuarios Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Usuarios>, response: Response<Usuarios>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Usuarios
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Usuario(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}