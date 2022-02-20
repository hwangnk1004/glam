package com.example.cupist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupist.data.ProfileResponseData
import com.example.cupist.repository.HomeRepository
import com.example.cupist.repository.RemoteHomeDataSource
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repo = HomeRepository(RemoteHomeDataSource())

    private val _introduceText = MutableLiveData<String>()
    val introduceText: LiveData<String> = _introduceText

    private val _jobText = MutableLiveData<String>()
    val jobText: LiveData<String> = _jobText

    private val _profile = MutableLiveData<ProfileResponseData>()
    val profile: LiveData<ProfileResponseData> = _profile

    fun textWatch(text: CharSequence, type: Int) {
        when (type) {
            0 -> _introduceText.value = text.toString()
            else -> _jobText.value = text.toString()
        }
    }

    fun fetchProfileData() {
        viewModelScope.launch {
            val result: ProfileResponseData? = repo.fetchProfile()
            result?.let {
                _profile.value = it
            }
        }
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }

}