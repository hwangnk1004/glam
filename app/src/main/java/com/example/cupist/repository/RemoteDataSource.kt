package com.example.cupist.repository

import com.example.cupist.allinterface.GetDataCallback
import com.example.cupist.allinterface.RetrofitApi
import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test.dev.cupist.de/")
        .client(
            OkHttpClient()
                .newBuilder()
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var retrofitApi: RetrofitApi = retrofit.create(RetrofitApi::class.java)


    fun fetchTodayRecommend(
        callback: GetDataCallback<IntroductionResponseData>
    ) {
        sendCallback(retrofitApi.fetchTodayReCommand(), callback)
    }

    fun fetchAddRecommend(
        callback: GetDataCallback<IntroductionResponseData>
    ) {
        sendCallback(retrofitApi.fetchAddReCommand(), callback)
    }

    fun fetchTargetRecommend(
        callback: GetDataCallback<IntroductionResponseData>
    ) {
        sendCallback(retrofitApi.fetchTargetReCommand(), callback)
    }

    fun fetchProfile(
        callback: GetDataCallback<ProfileResponseData>
    ) {
        sendCallback(retrofitApi.fetchProfile(), callback)
    }

    private fun <T> sendCallback(data: Call<T>, callback: GetDataCallback<T>) {
        data.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onFail()
            }

        })
    }
//    private fun <T> sendCallback(data: Response<T>, callback: GetDataCallback<T>) {
//        when {
//            data.isSuccessful -> callback.onSuccess(data.body())
//            else -> callback.onFail()
//        }
//    }
}


