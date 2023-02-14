package com.testing.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("emoji")
    @Expose
    val emoji: String?,
    @SerializedName("latitude")
    @Expose
    val latitude: Int?,
    @SerializedName("longitude")
    @Expose
    val longitude: Int?,
    @SerializedName("name")
    @Expose
    val name: String?
)