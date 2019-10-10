package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Usuarios
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UsuariosApi {
    //view

    @GET("usuarios/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Usuarios>

    //create
    @FormUrlEncoded
    @POST("usuarios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("FechaNacimiento") FechaNacimiento:String,
        @Field("IdUsuarioRol") IdUsuarioRol:Int,
        @Field("Telefono") Telefono:String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String

    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuarios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("FechaNacimiento") FechaNacimiento:String,
        @Field("IdUsuarioRol") IdUsuarioRol:Int,
        @Field("Telefono") Telefono:String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuarios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

    // Registro del usuario
    @FormUrlEncoded
    @POST("usuarios/registro")
    fun Registro(
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("Fecha de Nacimiento") FechaNacimiento:String,
        @Field("Telefono") Telefono:String

    ):Call<responseModel>

    @GET("usuarios/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

}