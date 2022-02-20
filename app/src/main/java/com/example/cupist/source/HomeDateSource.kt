package com.example.cupist.source

import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData

interface HomeDateSource {

    suspend fun fetchTodayReCommand(): IntroductionResponseData?

    suspend fun fetchAdditionalReCommand(index: Int): IntroductionResponseData?

    suspend fun fetchTargetReCommand(): IntroductionResponseData?

    suspend fun fetchProfile(): ProfileResponseData?

}