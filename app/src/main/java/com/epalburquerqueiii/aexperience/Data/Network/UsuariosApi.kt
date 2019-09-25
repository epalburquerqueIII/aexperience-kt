package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Usuarios
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UsuariosApi {
    //view

    @GET("usuario/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Usuarios>

    //create
    @FormUrlEncoded
    @POST("usuario/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("Tipo") Tipo:Int,
        @Field("Telefono") Telefono:String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String

    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuario/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String,
        @Field("Nif") Nif:String,
        @Field("Email") Email:String,
        @Field("Tipo") Tipo:Int,
        @Field("Telefono") Telefono:String,
        @Field("SesionesBonos") SesionesBonos:Int,
        @Field("Newsletter") Newsletter:Int,
        @Field("FechaBaja") FechaBaja:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"usuario/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}