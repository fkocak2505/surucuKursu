package com.bakiyem.surucu.proje.model.kadromuz

import com.google.gson.annotations.SerializedName

data class Response4Kadromuz(
    @SerializedName("Keyi")
    var key: String? = null,
    @SerializedName("AdSoyad")
    var adSoyad: String? = null,
    @SerializedName("Gorevi")
    var gorevi: String? = null,
    @SerializedName("Detay")
    var detay: String? = null,
    @SerializedName("Resim")
    var resim: String? = null
)


