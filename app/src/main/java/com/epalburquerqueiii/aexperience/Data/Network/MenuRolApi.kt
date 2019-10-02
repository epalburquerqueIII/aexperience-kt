package com.epalburquerqueiii.aexperience.Data.Network

import com.epalburquerqueiii.aexperience.BuildConfig
import com.epalburquerqueiii.aexperience.Data.Model.MenuRoles
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

    //create
    @FormUrlEncoded
    @POST("menuroles/"+BuildConfig.CREATE_DATA)
    fun Create(
        @Field("idMenu")idMenu:Int,
        @Field("idUsuarioRoles")idUsuarioRoles:Int
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menuroles/"+BuildConfig.UPDATE_DATA)
    fun Update(
        @Field("Id")id: Int,
        @Field("idMenu")idMenu:Int,
        @Field("idUsuarioRoles")idUsuarioRoles: Int
    ):Call<responseModel>

    //delete
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+"menuroles/"+BuildConfig.DELETE_DATA)
    fun Delete(
        @Field("Id")id:Int

    ):Call<responseModel>

}