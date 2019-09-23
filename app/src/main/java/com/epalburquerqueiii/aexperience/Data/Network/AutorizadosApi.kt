package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Autorizados
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AutorizadosApi {
    //view

    @GET("autorizados/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Autorizados>

    //create
    @FormUrlEncoded
    @POST("autorizados/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Idusuario")IdUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizados/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Idusuario")IdUsuario:Int,
        @Field("NombreAutorizado") NombreAutorizado:String,
        @Field("Nif") Nif:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"autorizados/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}