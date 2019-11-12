package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Espacios
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface EspaciosApi {

    // List
    @FormUrlEncoded
    @POST("espacios/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Espacios>

    //create
    @FormUrlEncoded
    @POST("espacios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Descripcion") Descripcion:String,
        @Field("Estado") Estado: Int,
        @Field("Modo") Modo: Int,
        @Field("Precio") Precio:Int,
        @Field("IDTipoevento") IDTipoevento:Int,
        @Field("Fecha") Fecha:String,
        @Field("Aforo") Aforo:Int,
        @Field("NumeroReservaslimite") NumeroReservaslimite:Int




        ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"espacios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id: Int,
        @Field("Descripcion")Descripcion:String,
        @Field("Estado") Estado:Int,
        @Field("Modo") Modo:Int,
        @Field("Precio") Precio:Int,
        @Field("IDTipoevento") IDTipoevento:Int,
        @Field("Fecha") Fecha:String,
        @Field("Aforo") Aforo:Int,
        @Field("NumeroReservaslimite") NumeroReservaslimite:Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"espacios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id:Int

    ):Call<responseModel>

    @FormUrlEncoded
    @POST("espacios/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Options>

}