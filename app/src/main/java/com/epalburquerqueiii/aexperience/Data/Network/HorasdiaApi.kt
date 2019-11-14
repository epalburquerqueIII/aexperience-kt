package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Horasdias
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface HorasdiaApi {
     // List
    @FormUrlEncoded
    @POST ("horarios/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Horasdias>

    //create
    @FormUrlEncoded
    @POST("horarios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("IDEspacio")IdEspacio:Int,
        @Field("Fecha") Fecha:String,
        @Field("Hora")IdHora:Int,
        @Field("Horareservada")Horareservada:Int,
        @Field("Reservado")Reservado:Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDAutorizado")IDAutorizado:Int



    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")ID: Int,
        @Field("IDEspacio")IdEspacio:Int,
        @Field("Fecha") Fecha:String,
        @Field("Hora")IdHora:Int,
        @Field("Horareservada")Horareservada:Int,
        @Field("Reservado")Reservado:Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDAutorizado")IDAutorizado:Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")Id:Int

    ):Call<responseModel>

}