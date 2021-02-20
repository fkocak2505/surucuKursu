package com.bakiyem.surucu.proje.model.galeri

import com.google.gson.annotations.SerializedName

data class Response4Galeri(
    @SerializedName("Keyi")
    var key: String? = null,
    @SerializedName("Baslik")
    var baslik: String? = null,
    @SerializedName("Resim")
    var resim: String? = null
)