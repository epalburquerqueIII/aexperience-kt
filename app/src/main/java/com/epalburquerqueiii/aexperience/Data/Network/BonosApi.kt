package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Bonos
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface BonosApi {
    //view

    @GET("bonos/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Bonos>

    //create
    @FormUrlEncoded
    @POST("bonos/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("ID")id:Int,
        @Field("Precio")Precio:Float,
        @Field("Sesiones") Sesiones:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"bonos/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id:Int,
        @Field("Precio")Precio: Float,
        @Field("Sesiones")Sesiones:Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"bonos/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}