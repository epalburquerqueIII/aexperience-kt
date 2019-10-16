package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Pagos
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface PagosApi {
    //view

    @GET("pagos/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Pagos>

    //create
    @FormUrlEncoded
    @POST("pagos/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IdReserva")IdReserva:Int,
        @Field("FechaPago") FechaPago:String,
        @Field("IdTipopago") IdTipopago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagos/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("Id") Id:Int,
        @Field("IdReserva")IdReserva:Int,
        @Field("FechaPago") FechaPago:String,
        @Field("IdTipopago") IdTipopago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagos/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

}