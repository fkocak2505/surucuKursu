package com.bakiyem.surucu.proje.model.hakkimizda

import com.google.gson.annotations.SerializedName

data class Response4Hakkimizda(

    @SerializedName("Keyi")
    val key: String? = null,

    @SerializedName("Detay")
    val detay: String? = null,

    @SerializedName("Baslik")
    val baslik: String? = null,

    @SerializedName("Resim")
    val resim: String? = null,

    @SerializedName("Icon")
    val icon: String? = null,


)