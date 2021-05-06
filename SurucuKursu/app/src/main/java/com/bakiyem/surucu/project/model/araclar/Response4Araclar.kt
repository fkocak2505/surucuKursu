package com.bakiyem.surucu.project.model.araclar

import com.google.gson.annotations.SerializedName

data class Response4Araclar(
    @SerializedName("Keyi")
    var key: String,
    @SerializedName("Model")
    var model: String,
    @SerializedName("Sinif")
    var sinif: String,
    @SerializedName("Resim")
    var resim: String
)