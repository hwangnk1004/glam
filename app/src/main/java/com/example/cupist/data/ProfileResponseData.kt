package com.example.cupist.data

import com.google.gson.annotations.SerializedName

data class ProfileResponseData(
    @SerializedName("data")
    var data: ProfileData? = null,

    @SerializedName("meta")
    var meta: ProfileMetaData? = null
)

data class ProfileData(
    @SerializedName("birthday")
    var birthday: String? = null,

    @SerializedName("body_type")
    var body_type: String? = null,

    @SerializedName("company")
    var company: String? = null,

    @SerializedName("education")
    var education: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

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
    var pictures: List<String>? = null,

    @SerializedName("school")
    var school: String? = null
)

data class ProfileMetaData(
    @SerializedName("body_types")
    var bodyTypes: List<MetaData>? = null,

    @SerializedName("educations")
    var educations: List<MetaData>? = null,

    @SerializedName("genders")
    var genders: List<MetaData>? = null,

    @SerializedName("height_range")
    var heightRange: HeightRangeData? = null,
)

data class MetaData(
    @SerializedName("key")
    var key: String? = null,

    @SerializedName("name")
    var name: String? = null
)

data class HeightRangeData(
    @SerializedName("max")
    var max: Int? = null,

    @SerializedName("min")
    var min: Int? = null
)