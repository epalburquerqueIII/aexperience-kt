package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Menus
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface MenusApi {
    //view

    @GET("menus/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Menus>

    //create
    @FormUrlEncoded
    @POST("menus/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("ParentId")ParentId:Int,
        @Field("Orden")Orden:Int,
        @Field("Titulo") Titulo:String,
        @Field("Icono") Icono:String,
        @Field("Url") Url:String,
        @Field("HandleFunc") HandleFunc:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menus/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("Id")id: Int,
        @Field("ParentId")IDUsuario:Int,
        @Field("Orden")Orden:Int,
        @Field("Titulo") Titulo:String,
        @Field("Icono") Icono:String,
        @Field("Url") Url:String,
        @Field("HandleFunc") HandleFunc:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menus/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

}