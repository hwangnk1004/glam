package com.example.cupist.repository

import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData
import com.example.cupist.network.ApiUrls
import com.example.cupist.network.HomeApi
import com.example.cupist.source.HomeDateSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class RemoteHomeDataSource : HomeDateSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiUrls.BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var homeApi: HomeApi = retrofit.create(HomeApi::class.java)

    override suspend fun fetchTodayReCommand(): IntroductionResponseData? {
        return runCatching { homeApi.fetchTodayReCommand().awaitResponse() }.getOrNull()?.body()
    }

    override suspend fun fetchAdditionalReCommand(index: Int): IntroductionResponseData? {
        return runCatching {
            val call = if (index <= 1) {
                homeApi.fetchAddReCommand()
            } else {
                homeApi.fetchAddReCommand(index)
            }
            call.awaitResponse()
        }.getOrNull()?.body()
    }

    override suspend fun fetchTargetReCommand(): IntroductionResponseData? {
        return runCatching { homeApi.fetchTargetReCommand().awaitResponse() }.getOrNull()?.body()
    }

    override suspend fun fetchProfile(): ProfileResponseData? {
        return runCatching { homeApi.fetchProfile().awaitResponse() }.getOrNull()?.body()
    }

}

