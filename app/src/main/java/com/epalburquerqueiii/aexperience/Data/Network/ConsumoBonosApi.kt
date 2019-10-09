package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBonos
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ConsumoBonosApi {
    //view

    @GET("consumobonos/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<ConsumoBonos>

    //create
    @FormUrlEncoded
    @POST("consumobonos/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Fecha")Fecha:String,
        @Field("Sesiones")Sesiones:Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDEspacio") IDEspacio:Int,
        @Field("IDAutorizado") IDAutorizado:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"consumobonos/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Fecha")Fecha:String,
        @Field("Sesiones")Sesiones:Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("IDEspacio") IDEspacio:Int,
        @Field("IDAutorizado") IDAutorizado:Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"consumobonos/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}