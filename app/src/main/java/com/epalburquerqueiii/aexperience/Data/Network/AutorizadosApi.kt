package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Autorizados
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AutorizadosApi {
    //view

    @GET("autorizado/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Autorizados>

    //create
    @FormUrlEncoded
    @POST("autorizado/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IDUsuario")IDUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizado/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizado/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id: Int

    ):Call<responseModel>

    @GET("autorizados/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

}