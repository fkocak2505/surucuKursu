package com.bakiyem.surucu.proje.base.model

import com.google.gson.annotations.SerializedName

data class ResResult<T>(

    @SerializedName("hata")
    var isError: Boolean,
    @SerializedName("hataMesaj")
    var isErrorMessage: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("data")
    var data: T,
)

data class ResResultArray<T>(

    @SerializedName("hata")
    var isError: Boolean,
    @SerializedName("hataMesaj")
    var isErrorMessage: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("data")
    var data: MutableList<T>,
)