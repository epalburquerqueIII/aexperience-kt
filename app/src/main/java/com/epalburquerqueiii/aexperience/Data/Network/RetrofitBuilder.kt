package com.epalburquerqueiii.aexperience.Data.Network

import android.content.Context
import com.epalburquerqueiii.aexperience.BuildConfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    fun builder(context: Context):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}