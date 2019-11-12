package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Usuarios
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Model.responseModelAuth
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UsuariosApi {
    //List

    @FormUrlEncoded
    @POST("usuarios/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Usuarios>

    //create
    @FormUrlEncoded
    @POST("usuarios/"+BuildConfig.CREATE_DATA)
    fun create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("FechaNacimiento") FechaNacimiento:String,
        @Field("IdUsuarioRol") IdUsuarioRol:Int,
        @Field("Telefono") Telefono:String,
        @Field("Password") Password:String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String
        ):Call<responseModel>


    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuarios/"+BuildConfig.UPDATE_DATA)
    fun update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("FechaNacimiento") FechaNacimiento:String,
        @Field("IdUsuarioRol") IdUsuarioRol:Int,
        @Field("Telefono") Telefono:String,
        @Field("Password") Password: String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String
        ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuarios/"+BuildConfig.DELETE_DATA)
    fun delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id:Int
        ):Call<responseModel>
 //registrar
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuarios/register")
    fun register(
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("FechaNacimiento") FechaNacimiento:String,
        @Field("Telefono") Telefono:String,
        @Field("Password") Password:String
        ):Call<responseModel>

    //Login
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"login")
    fun login(
        @Field("email")Nombre:String,
        @Field("password") Nif:String
        ):Call<responseModelAuth>

    @FormUrlEncoded
    @POST("usuarios/"+BuildConfig.GETOPTIONS_DATA)
    fun getOptions(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
        ) :Call<Options>

}