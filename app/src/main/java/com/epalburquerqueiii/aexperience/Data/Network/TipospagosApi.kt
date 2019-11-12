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
// List
    @FormUrlEncoded
    @GET("tipospago/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
    @Field("X-CSRF-Token") CSRFToken:String
) :Call<Tipospagos>

    //create
    @FormUrlEncoded
    @POST("tipospago/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Nombre")Nombre:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tipospago/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id: Int,
        @Field("Nombre") Nombre: String

    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"tipospago/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id:Int

    ):Call<responseModel>
    // Options
    @FormUrlEncoded
    @POST("tipospago/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Options>

}