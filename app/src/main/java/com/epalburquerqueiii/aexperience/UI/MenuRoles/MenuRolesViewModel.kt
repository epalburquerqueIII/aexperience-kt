package com.epalburquerqueiii.aexperience.UI.Menus

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.MenuRol
import com.epalburquerqueiii.aexperience.Data.Model.MenuRoles
import com.epalburquerqueiii.aexperience.Data.Network.MenuRolApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuRolesViewModel : ViewModel() {


//    private val MenusUseCase = MenusUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<MenuRol>>()

    private fun setRegistros(listregistros:ArrayList<MenuRol>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<MenuRol>> {
        return registros
    }

    fun getRecords():ArrayList<MenuRol> {

        var datos = ArrayList<MenuRol>()

        val get = RetrofitBuilder.builder().create(MenuRolApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<MenuRoles> {
            override fun onFailure(call: Call<MenuRoles>, t: Throwable) {
                Log.i("Menus Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<MenuRoles>, response: Response<MenuRoles>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as MenuRoles
//                val size = response.Records!!.size
                datos = response.Records!!
                setRegistros(datos)
            }
        })
/*
        if (datos.size == 0) {
            datos!!.add(Menu(0,"No hay registros","","",0,"",""))
        }
*/
        return datos
    }

    public fun Load()=changed.set(false)
    public fun make_Change()=changed.set(true)

}