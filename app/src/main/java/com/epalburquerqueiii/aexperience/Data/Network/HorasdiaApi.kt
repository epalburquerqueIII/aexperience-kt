package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Horasdias
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface HorasdiaApi {
    //viewado/"+BuildConfig.VIEW_DATA)
    @GET ("horarios/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Horasdias>

    //create
    @FormUrlEncoded
    @POST("horarios/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IdEspacio")IdEspacio:Int,
        @Field("Fecha") Fecha:String,
        @Field("IdHora")IdHora:Int


    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")ID: Int,
        @Field("IdEspacio")IdEspacio:Int,
        @Field("Fecha") Fecha:String,
        @Field("IdHora")IdHora:Int

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"horarios/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")Id:Int

    ):Call<responseModel>

}