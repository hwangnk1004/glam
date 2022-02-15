package com.example.cupist.repository

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

object RemoteDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test.dev.cupist.de")
        .client(
            OkHttpClient()
                .newBuilder()
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private var retrofitService: RetrofitServiceV2 = retrofit.create(RetrofitServiceV2::class.java)

}

interface RetrofitServiceV2 {
    // 오늘의 추천
    @GET("/introduction")
    fun fetchTodayReCommand(): Call<ResponseBody>

    // 추가 추천
    @GET("/introduction/additional")
    fun fetchAddReCommand(): Call<ResponseBody>

    // 맞춤 추천
    @POST("/introduction/custom")
    fun fetchTargetReCommand(): Call<ResponseBody>

    // 내 프로필
    @GET("/profile")
    fun fetchProfile(): Call<ResponseBody>

}
