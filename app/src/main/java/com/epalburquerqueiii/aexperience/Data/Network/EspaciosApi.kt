package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Espacios
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface EspaciosApi {


    @GET("espacios/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Espacios>

    //create
    @FormUrlEncoded
    @POST("espacios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Descripcion") Descripcion:String,
        @Field("Estado") Estado: Int,
        @Field("Modo") Modo: Int,
        @Field("Precio") Precio:Int,
        @Field("IdTiposevento") IdTiposevento:Int,
        @Field("Fecha") Fecha:String,
        @Field("Aforo") Aforo:Int,
        @Field("NumeroReservaslimite") NumeroReservaslimite:Int




        ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"espacios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Descripcion")Descripcion:String,
        @Field("Estado") Estado:Int,
        @Field("Modo") Modo:Int,
        @Field("Precio") Precio:Int,
        @Field("IdTiposevento") IdTiposevento:Int,
        @Field("Fecha") Fecha:String,
        @Field("Aforo") Aforo:Int,
        @Field("NumeroReservaslimite") NumeroReservaslimite:Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"espacios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}