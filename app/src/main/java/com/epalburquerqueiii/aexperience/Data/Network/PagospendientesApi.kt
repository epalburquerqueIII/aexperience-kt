package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Pagospendientes
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface PagospendientesApi {
    //view

    @GET("pagospendientes/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Pagospendientes>

/*    //create
    @FormUrlEncoded
    @POST("pagospendientes/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IdReserva")IdReserva:Int,
        @Field("FechaPago") FechaPago: String,
        @Field("IdTipospago") IdTipospago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String,
        @Field("Importe") Importe:Float

    ):Call<responseModel>

 */

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagospendientes/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("IdReserva")IdReserva:Int,
        @Field("FechaPago") FechaPago: String,
        @Field("IdTipospago") IdTipospago:Int,
        @Field("NumeroTarjeta") NumeroTarjeta:String,
        @Field("Importe") Importe:Float
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"pagospendientes/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

}