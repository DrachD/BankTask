package com.testing.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetBankCustomerEntity(
    @SerializedName("bank")
    @Expose
    val bank: Bank?,
    @SerializedName("brand")
    @Expose
    val brand: String?,
    @SerializedName("country")
    @Expose
    val country: Country?,
    @SerializedName("number")
    @Expose
    val number: Number?,
    @SerializedName("prepaid")
    @Expose
    val prepaid: Boolean?,
    @SerializedName("scheme")
    @Expose
    val scheme: String?,
    @SerializedName("type")
    @Expose
    val type: String?
)