package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.MenuRoles
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface MenuRolesApi {
    //view

    @GET("menuroles/"+BuildConfig.VIEW_DATA)
    fun Get(/*debe haber un encabezado o un cuerpo*/) :Call<MenuRoles>

    @GET("menuroles/"+BuildConfig.GETOPTIONS_DATA)
    fun GetOptions(/*debe haber un encabezado o un cuerpo*/) :Call<Options>

    //create
    @FormUrlEncoded
    @POST("menuroles/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("IDMenu")IDMenu:Int,
        @Field("IDUsuarioRoles")IDUsuarioRoles:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menuroles/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("ID")ID: Int,
        @Field("IDMenu")IDMenu:Int,
        @Field("IDUsuarioRoles")IDUsuarioRoles: Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menuroles/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("ID")ID:Int

    ):Call<responseModel>

}