package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Usuariosroles
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UsuariosrolesApi {

    //List
    @FormUrlEncoded
    @GET("usuariosroles/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Usuariosroles>

    //create
    @FormUrlEncoded
    @POST("usuariosroles/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Nombre")Nombre:String
    ):Call<responseModel>
//TODO hacer que el back-end devuelva el registro ID creado
    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuariosroles/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String
    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuariosroles/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id:Int

    ):Call<responseModel>

    @GET("usuariosroles/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Options>

}