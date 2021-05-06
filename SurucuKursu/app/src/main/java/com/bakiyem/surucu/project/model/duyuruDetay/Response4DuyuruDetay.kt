package com.bakiyem.surucu.project.model.duyuruDetay

import com.google.gson.annotations.SerializedName

data class Response4DuyuruDetay(
    @SerializedName("Keyi")
    var key: String? = null,
    @SerializedName("Baslik")
    var baslik: String? = null,
    @SerializedName("Detay")
    var detay: String? = null,
    @SerializedName("Tarih")
    var tarih: String? = null
)