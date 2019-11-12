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

// List
    @FormUrlEncoded
    @POST("autorizados/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String

    ) :Call<Autorizados>

    //create
    @FormUrlEncoded
    @POST("autorizados/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizados/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id: Int,
        @Field("IDUsuario")IDUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizados/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id: Int

    ):Call<responseModel>

    @FormUrlEncoded
    @POST("autorizados/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Options>

}