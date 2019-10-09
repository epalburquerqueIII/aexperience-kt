package com.epalburquerqueiii.aexperience.UI.Registros31

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Usuario
import com.epalburquerqueiii.aexperience.Data.Model.Usuarios
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registros31ViewModel : ViewModel() {

//    private val Registros31UseCase = Registros31UseCase()

    val changed = ObservableBoolean(false)


    private val registro = MutableLiveData<Usuario>()

    private fun setRegistro(registro: Usuario) {
        registro.value = listregistros
    }

    fun getRegistro() {
        setRegistro(getRecord())
    }

    fun getregistroLiveData(): LiveData<Usuario> {
        return registro
    }

    fun getRecord(): Usuario {

        var dato  = Usuario(0,"","","", null, 0, "", 0, "")
        setRegistro(dato)
        return dato
    }

    public fun Load() = changed.set(false)
    public fun make_Change() = changed.set(true)
}


