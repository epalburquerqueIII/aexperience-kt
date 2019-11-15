package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Reservas
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ReservasApi {
    //List
    @FormUrlEncoded
    @POST("reservas/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Reservas>

    //create
    @FormUrlEncoded
    @POST("reservas/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Fecha")Fecha:String,
        @Field("Hora") Hora:Int,
        @Field("IdUsuario")IdUsuario:Int,
        @Field("IdEspacio")IdEspacio:Int,
        @Field("IdAutorizado")IdAutorizado:Int

    ):Call<responseModel>
    //TODO hacer que el back-end devuelva el registro ID creado
    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id: Int,
        @Field("Fecha")Fecha:String,
        @Field("Hora") Hora:Int,
        @Field("IdUsuario")IdUsuario:Int,
        @Field("IdEspacio")IdEspacio:Int,
        @Field("IdAutorizado")IdAutorizado:Int
    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/"+BuildConfig.UPDATE_DATA)
    fun Reservahora(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("IdEspacioh")IdEspacioh:Int,
        @Field("Fecha")Fecha:String,
        @Field("ch1") Hora1:String,
        @Field("ch2") Hora2:String,
        @Field("ch3") Hora3:String,
        @Field("ch4") Hora4:String,
        @Field("ch5") Hora5:String,
        @Field("ch6") Hora6:String,
        @Field("ch7") Hora7:String,
        @Field("ch8") Hora8:String

    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("ID")id:Int

    ):Call<responseModel>
    @GET("reservas/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

    //ComprarBono
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"reservas/comprarbono")
    fun ComprarBono(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("IdUsuario") IdUsuario:Int,
        @Field("Sesiones") Sesiones:Int,
        @Field("TipoPago") TipoPago: Int





    ):Call<responseModel>

}