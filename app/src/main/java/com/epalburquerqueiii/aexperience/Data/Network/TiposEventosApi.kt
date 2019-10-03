package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.TiposEventos
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface TiposEventosApi {
    //view

    @GET("tiposeventos/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<TiposEventos>

    //create
    @FormUrlEncoded
    @POST("tiposeventos/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Nombre") Nombre:String

    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tiposeventos/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")id: Int,
        @Field("Nombre") Nombre:String

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tiposeventos/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")id:Int

    ):Call<responseModel>

}