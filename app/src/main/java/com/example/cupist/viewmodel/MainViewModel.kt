package com.example.cupist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupist.allinterface.GetDataCallback
import com.example.cupist.data.IntroductionResponseData
import com.example.cupist.data.ProfileResponseData
import com.example.cupist.repository.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class MainViewModel : ViewModel() {
    private val repo = Repository

    private val _todayRecommend = MutableLiveData<IntroductionResponseData>()
    val todayRecommend: LiveData<IntroductionResponseData> = _todayRecommend

    private val _addRecommend = MutableLiveData<IntroductionResponseData>()
    val addRecommend: LiveData<IntroductionResponseData> = _addRecommend

    private val _targetRecommend = MutableLiveData<IntroductionResponseData>()
    val targetRecommend: LiveData<IntroductionResponseData> = _targetRecommend

    private val _profile = MutableLiveData<ProfileResponseData>()
    val profile: LiveData<ProfileResponseData> = _profile

    fun fetchAllData() = viewModelScope.launch {
        _todayRecommend.value = fetchTodayRecommend()
        _addRecommend.value = fetchAddRecommend()
        _targetRecommend.value = fetchTargetRecommend()
    }

    fun fetchProfileData() = viewModelScope.launch {
        _profile.value = fetchProfile()
    }

    private suspend fun fetchTodayRecommend(): IntroductionResponseData? {
        return suspendCancellableCoroutine {
            repo.fetchTodayRecommend(object : GetDataCallback<IntroductionResponseData> {
                override fun onSuccess(data: IntroductionResponseData?) {
                    Log.d(TAG, "Today Recommend Success")
                    it.resumeWith(Result.success(data))
                }

                override fun onFail() {
                    Log.d(TAG, "Today Recommend Fail")
                    it.resumeWith(Result.success(null))
                }

            })
        }
    }

    private suspend fun fetchAddRecommend(): IntroductionResponseData? {
        return suspendCancellableCoroutine {
            repo.fetchAddRecommend(object : GetDataCallback<IntroductionResponseData> {
                override fun onSuccess(data: IntroductionResponseData?) {
                    Log.d(TAG, "Add Recommend Success")
                    it.resumeWith(Result.success(data))
                }

                override fun onFail() {
                    Log.d(TAG, "Add Recommend Fail")
                    it.resumeWith(Result.success(null))
                }

            })
        }
    }

    private suspend fun fetchTargetRecommend(): IntroductionResponseData? {
        return suspendCancellableCoroutine {
            repo.fetchTargetRecommend(object : GetDataCallback<IntroductionResponseData> {
                override fun onSuccess(data: IntroductionResponseData?) {
                    Log.d(TAG, "Target Recommend Success")
                    it.resumeWith(Result.success(data))
                }

                override fun onFail() {
                    Log.d(TAG, "Target Recommend Fail")
                    it.resumeWith(Result.success(null))
                }

            })
        }
    }

    private suspend fun fetchProfile(): ProfileResponseData? {
        return suspendCancellableCoroutine {
            repo.fetchProfile(object : GetDataCallback<ProfileResponseData> {
                override fun onSuccess(data: ProfileResponseData?) {
                    Log.d(TAG, "Profile Success")
                    it.resumeWith(Result.success(data))
                }

                override fun onFail() {
                    Log.d(TAG, "Profile Fail")
                    it.resumeWith(Result.success(null))
                }

            })
        }
    }

    companion object {
        const val TAG = "glamLog"
    }

}