package com.example.cupist.repository

import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData
import com.example.cupist.source.HomeDateSource

class HomeRepository(private val source: HomeDateSource) {

    private var additionalRecommendIndex = 1

    suspend fun fetchTodayRecommend(): IntroductionResponseData? {
        return source.fetchTodayReCommand()
    }

    suspend fun fetchAddRecommendMore(): IntroductionResponseData? {
        return source.fetchAdditionalReCommand(additionalRecommendIndex++)
    }

    suspend fun fetchTargetRecommend(): IntroductionResponseData? {
        return source.fetchTargetReCommand()
    }

    suspend fun fetchProfile(): ProfileResponseData? {
        return source.fetchProfile()
    }

}
