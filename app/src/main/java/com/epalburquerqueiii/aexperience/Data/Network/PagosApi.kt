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

    @FormUrlEncoded
    @POST("pagos/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Pagos>

    //create
    @FormUrlEncoded
    @POST("pagos/"+BuildConfig.CREATE_DATA)
    fun create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("IdReserva")IdReserva:Int,
      //  @Field("FechaPago") FechaPago:String,
        @Field("IdTipopago") IdTipopago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String,
        @Field("Importe") Importe:Float
        ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagos/"+BuildConfig.UPDATE_DATA)
    fun update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id") Id:Int,
        @Field("IdReserva")IdReserva:Int,
        @Field("FechaPago") FechaPago:String,
        @Field("IdTipopago") IdTipopago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String,
        @Field("Importe") Importe:Float
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagos/"+BuildConfig.DELETE_DATA)
    fun delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id:Int

    ):Call<responseModel>

}