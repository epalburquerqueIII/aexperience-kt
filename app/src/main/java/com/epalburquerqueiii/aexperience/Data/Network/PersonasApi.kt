package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Personas
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface PersonasApi {
    //view

    @GET("personas/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Personas>

    //create
    @FormUrlEncoded
    @POST("personas/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre")Nombre:String,
        @Field("Direccion") Direccion:String,
        @Field("Poblacion") Poblacion:String,
        @Field("Provinciaid") Provinciaid:Int,
        @Field("Telefono") Telefono:String,
        @Field("Email") Email:String
    ):Call<responseModel>
//TODO hacer que el back-end devuelva el registro ID creado
    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"personas/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Nombre")Nombre:String,
        @Field("Direccion") Direccion:String,
        @Field("Poblacion") Poblacion:String,
        @Field("Provinciaid") Provinciaid:Int,
        @Field("Telefono") Telefono:String,
        @Field("Email") Email:String
    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"personas/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}