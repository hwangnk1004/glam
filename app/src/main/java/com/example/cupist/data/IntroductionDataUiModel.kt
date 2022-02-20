package com.example.cupist.data

data class IntroductionDataUiModel(
    val itemType: HomeItemType,
    val onClick: (IntroductionDataUiModel) -> Unit,
    val todayRecommend: Boolean,
    val age: Int,
    val company: String,
    val distance: Int,
    val height: Int,
    val id: Int,
    val introduction: String,
    val job: String?,
    val location: String,
    val name: String,
    val pictures: List<String>
) {
    companion object {
        fun newInstance(itemType: HomeItemType, data: IntroductionData, onClick: (IntroductionDataUiModel) -> Unit): IntroductionDataUiModel {
            return IntroductionDataUiModel(
                itemType = itemType,
                onClick = onClick,
                todayRecommend = itemType == HomeItemType.TODAY,
                age = data.age ?: 0,
                company = data.company ?: "",
                distance = data.distance ?: 0,
                height = data.height ?: 0,
                id = data.id ?: 0,
                introduction = data.introduction ?: "",
                job = data.job ?: "",
                location = data.location ?: "",
                name = data.name ?: "",
                pictures = data.pictures ?: emptyList()
            )
        }

        fun empty(itemType: HomeItemType, onClick: (IntroductionDataUiModel) -> Unit): IntroductionDataUiModel {
            return IntroductionDataUiModel(
                itemType = itemType,
                onClick = onClick,
                todayRecommend = itemType == HomeItemType.TODAY,
                age = 0,
                company = "",
                distance = 0,
                height = 0,
                id = 0,
                introduction = "",
                job = "",
                location = "",
                name = "",
                pictures = emptyList()
            )
        }
    }

}