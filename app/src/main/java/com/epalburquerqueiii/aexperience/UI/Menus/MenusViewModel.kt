package com.epalburquerqueiii.aexperience.UI.Menus

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Menu
import com.epalburquerqueiii.aexperience.Data.Model.Menus
import com.epalburquerqueiii.aexperience.Data.Network.MenusApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenusViewModel : ViewModel() {


//    private val MenusUseCase = MenusUseCase()

    val changed = ObservableBoolean(false)


    private val registros = MutableLiveData<ArrayList<Menu>>()

    private fun setRegistros(listregistros:ArrayList<Menu>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Menu>> {
        return registros
    }

    fun getRecords():ArrayList<Menu> {

        var datos = ArrayList<Menu>()

        val get = RetrofitBuilder.builder().create(MenusApi::class.java)
        val callget = get.List(AppData.CsrfRef)
        callget.enqueue(object : Callback<Menus> {
            override fun onFailure(call: Call<Menus>, t: Throwable) {
                Log.i("Menus Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Menus>, response: Response<Menus>) {

                @Suppress("NAME_SHADOWING")
                val response = response.body() as Menus
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

    fun Load()=changed.set(false)
    fun make_Change()=changed.set(true)

}