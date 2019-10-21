package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Eventos
import com.epalburquerqueiii.aexperience.Data.Model.Options

import retrofit2.Call
import retrofit2.http.GET


interface MenuParentApi {
    //view

    @GET("menus/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

    //@GET("eventos/getEventosmdtojson") cambio de minuscula por Mayuscula
    @GET("eventos/getEventosmdtojson")
    fun getEventos(): Call<Eventos>
}