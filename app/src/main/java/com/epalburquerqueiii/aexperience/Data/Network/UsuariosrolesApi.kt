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
    //view

    @GET("usuariosRoles/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Usuariosroles>

    //create
    @FormUrlEncoded
    @POST("usuariosRoles/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre")Nombre:String
    ):Call<responseModel>
//TODO hacer que el back-end devuelva el registro ID creado
    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuariosRoles/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String
    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuariosRoles/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

    @GET("usuariosRoles/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

}