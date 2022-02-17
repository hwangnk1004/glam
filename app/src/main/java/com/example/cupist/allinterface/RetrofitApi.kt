package com.example.cupist.allinterface

import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    // 오늘의 추천
    @GET("introduction")
    fun fetchTodayReCommand(): Call<IntroductionResponseData>

    // 추가 추천
    @GET("/introduction/additional")
    fun fetchAddReCommand(): Call<IntroductionResponseData>

    // 맞춤 추천
    @POST("/introduction/custom")
    fun fetchTargetReCommand(): Call<IntroductionResponseData>

    // 내 프로필
    @GET("/profile")
    fun fetchProfile(): Call<ProfileResponseData>
}