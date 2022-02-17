package com.example.cupist.repository

import com.example.cupist.allinterface.GetDataCallback
import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData

object Repository {
    private val source = RemoteDataSource

    fun fetchTodayRecommend(callback: GetDataCallback<IntroductionResponseData>) {
        source.fetchTodayRecommend(callback)
    }

    fun fetchAddRecommend(callback: GetDataCallback<IntroductionResponseData>) {
        source.fetchAddRecommend(callback)
    }

    fun fetchTargetRecommend(callback: GetDataCallback<IntroductionResponseData>) {
        source.fetchTargetRecommend(callback)
    }

    fun fetchProfile(callback: GetDataCallback<ProfileResponseData>) {
        source.fetchProfile(callback)
    }

}
