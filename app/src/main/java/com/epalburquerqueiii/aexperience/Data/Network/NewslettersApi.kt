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

    //List
    @FormUrlEncoded
    @POST("newsletter/"+BuildConfig.VIEW_DATA)
    fun List(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Newsletters>

    //create
    @FormUrlEncoded
    @POST("newsletter/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Email")Email:String,
        @Field("Idtiponoticias")Idtiponoticias:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id: Int,
        @Field("Email")Email:String,
        @Field("Idtiponoticias")Idtiponoticias:Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id:Int
    ):Call<responseModel>

    //SaveNewsletterUser
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"newsletter/newsletterguardar")
    fun SaveNewsletterUser(
        @Field("X-CSRF-Token") CSRFToken:String,
        @Field("Id")id: Int,
        @Field("Email")Email:String,
        @Field("nwch1")nwch1:Int,
        @Field("nwch2")nwch2:Int,
        @Field("nwch3")nwch3:Int,
        @Field("nwch4")nwch4:Int,
        @Field("nwch5")nwch5:Int,
        @Field("nwch6")nwch6:Int,
        @Field("nwch7")nwch7:Int,
        @Field("nwch8")nwch8:Int,
        @Field("nwch9")nwch9:Int,
        @Field("nwch10")nwch10:Int
    ):Call<responseModel>

    @FormUrlEncoded
    @POST("newsletter/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/
        @Field("X-CSRF-Token") CSRFToken:String
    ) :Call<Options>

}