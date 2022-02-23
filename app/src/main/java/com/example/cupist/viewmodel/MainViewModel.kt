package com.example.cupist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupist.data.HomeItemType
import com.example.cupist.data.IntroductionDataUiModel
import com.example.cupist.repository.HomeRepository
import com.example.cupist.repository.RemoteHomeDataSource
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = HomeRepository(RemoteHomeDataSource())

    private val _homeAllData: MutableLiveData<List<IntroductionDataUiModel>> = MutableLiveData()
    val homeAllData: LiveData<List<IntroductionDataUiModel>> = _homeAllData

    fun fetchAllData() {
        viewModelScope.launch {
            val todayResult = repo.fetchTodayRecommend()
            val additionalResult = repo.fetchAddRecommendMore()

            val toDayUiModelList = todayResult?.data?.map { introductionData ->
                IntroductionDataUiModel.newInstance(HomeItemType.TODAY, introductionData) {
                    handleClickPerson(it)
                }
            }.orEmpty()

            val additionalUiModelList = additionalResult?.data?.map { introductionData ->
                IntroductionDataUiModel.newInstance(HomeItemType.ADDITIONAL, introductionData) {
                    handleClickPerson(it)
                }
            }.orEmpty()

            _homeAllData.value = toDayUiModelList
                .plus(IntroductionDataUiModel.empty(HomeItemType.TARGET_LAYOUT) {
                    handleClickTargetRecommend()
                })
                .plus(additionalUiModelList)
        }
    }

    fun fetchMore() {
        viewModelScope.launch {
            val result = repo.fetchAddRecommendMore()
            // list를 통째로 다른 타입으로 캐스팅할 때 사용.
            val additionalUiModelList = result?.data?.map { introductionData ->
                IntroductionDataUiModel.newInstance(HomeItemType.ADDITIONAL, introductionData) {
                    handleClickPerson(it)
                }
            }.orEmpty()
            val current = homeAllData.value.orEmpty()
            _homeAllData.value = current.plus(additionalUiModelList)
        }
    }

    private fun handleClickPerson(item: IntroductionDataUiModel) {
        val currentList = homeAllData.value?.toMutableList()
        val iterator = currentList?.iterator() ?: return
        var removed = false
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (next.id == item.id) {
                iterator.remove()
                removed = true
                break
            }
        }
        if (removed) {
            _homeAllData.value = currentList
        }
    }

    private fun handleClickTargetRecommend() {
        viewModelScope.launch {
            val result = repo.fetchTargetRecommend()
            val toAddUiModels = result?.data?.map { introductionData ->
                IntroductionDataUiModel.newInstance(HomeItemType.TARGET_ITEM, introductionData) {
                    handleClickPerson(it)
                }
            }.orEmpty()
            _homeAllData.value = toAddUiModels.plus(homeAllData.value.orEmpty())
        }
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

}