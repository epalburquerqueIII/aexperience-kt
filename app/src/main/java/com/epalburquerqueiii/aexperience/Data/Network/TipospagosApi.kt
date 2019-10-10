package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Tipospagos
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface TipospagosApi {
    //view

    @GET("tipospagos/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

    @GET("tipospagos/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Tipospagos>

    //create
    @FormUrlEncoded
    @POST("tipospagos/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre")Nombre:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tipospagos/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("Id")id: Int,
        @Field("Nombre") Nombre: String

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tipospagos/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

}