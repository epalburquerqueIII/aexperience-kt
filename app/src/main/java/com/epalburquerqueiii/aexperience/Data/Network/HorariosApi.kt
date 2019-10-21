package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Horarios
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface HorariosApi {
    //viewado/"+BuildConfig.VIEW_DATA)
    @GET ("horarios/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Horarios>

    //create
    @FormUrlEncoded
    @POST("horarios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IDEspacio")IDEspacio:Int,
        @Field("Descripcion") Descripcion:String,
        @Field("Fechainicio") Fechainicio:String,
        @Field("Fechafinal") Fechafinal:String,
        @Field("Hora")Hora:Int,
        @Field("HorasReservadas") HorasReservadas:Int

    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")ID: Int,
        @Field("IDEspacio")IDEspacio:Int,
        @Field("Descripcion") Descripcion:String,
        @Field("Fechainicio") Fechainicio:String,
        @Field("Fechafinal") Fechafinal:String,
        @Field("Hora") Hora: Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")Id:Int

    ):Call<responseModel>

}