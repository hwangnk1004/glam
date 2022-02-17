package com.example.cupist.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IntroductionResponseData(
    @SerializedName("data")
    @Expose
    var data: List<IntroductionData>? = null,

    @SerializedName("meta")
    @Expose
    var meta: IntroductionMetaData? = null
)

data class IntroductionData(
    @SerializedName("age")
    var age: Int? = null,

    @SerializedName("company")
    var company: String? = null,

    @SerializedName("distance")
    var distance: Int? = null,

    @SerializedName("height")
    var height: Int? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("introduction")
    var introduction: String? = null,

    @SerializedName("job")
    var job: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("pictures")
    var pictures: List<String>? = null
)

data class IntroductionMetaData(
    @SerializedName("next")
    var next: IntroductionMetaNextData? = null

)

data class IntroductionMetaNextData(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("method")
    var method: String? = null,
    @SerializedName("url")
    var url: String? = null

)
