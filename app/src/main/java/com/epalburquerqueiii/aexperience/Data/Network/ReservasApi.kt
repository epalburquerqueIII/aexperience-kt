package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Reservas
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*


interface ReservasApi {
    //view

    @GET("reservas/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Reservas>

    //create
    @FormUrlEncoded
    @POST("reservas/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Fecha")Fecha:String,
        @Field("FechaPago")FechaPago:String,
        @Field("Hora")Hora:Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDEspacio")IDEspacio:Int,
        @Field("IDAutorizado")IDAutorizado:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")ID: Int,
        @Field("Fecha")Fecha: String,
        @Field("FechaPago")FechaPago: String,
        @Field("Hora")Hora: Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDEspacio")IDEspacio:Int,
        @Field("IDAutorizado")IDAutorizado:Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")ID:Int

    ):Call<responseModel>

}