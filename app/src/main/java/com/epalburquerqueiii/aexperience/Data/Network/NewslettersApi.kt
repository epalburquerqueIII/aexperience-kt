package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.Newsletters
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface NewslettersApi {
    //view

    @GET("newsletter/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<Newsletters>

    //create
    @FormUrlEncoded
    @POST("newsletter/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("Email")Email:String,
        @Field("Idtiponoticias")Idtiponoticias:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("Id")id: Int,
        @Field("Email")Email:String,
        @Field("Idtiponoticias")Idtiponoticias:Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

    //SaveNewsletterUser
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/newsletterguardar")
    fun SaveNewsletterUser(
        @Field("Id")id: Int,
        @Field("Email")Email:String,
        @Field("ch1")ch1:Int,
        @Field("ch2")ch2:Int,
        @Field("ch3")ch3:Int,
        @Field("ch4")ch4:Int,
        @Field("ch5")ch5:Int,
        @Field("ch6")ch6:Int,
        @Field("ch7")ch7:Int,
        @Field("ch8")ch8:Int,
        @Field("ch9")ch9:Int,
        @Field("ch10")ch10:Int
    ):Call<responseModel>

    @GET("newsletter/getoptions"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

}