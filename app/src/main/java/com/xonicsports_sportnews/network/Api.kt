package com.xonicsports_sportnews.network

import com.xonicsports_sportnews.dataClasses.FlexTonics
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface Api {
    @GET("worldcup.json")
    suspend fun getData() : Response<FlexTonics>
    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : Api {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                .readTimeout(2, TimeUnit.MINUTES) // read timeout
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://trendingnewske.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}