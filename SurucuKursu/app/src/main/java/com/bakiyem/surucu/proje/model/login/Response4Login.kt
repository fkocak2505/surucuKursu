package com.bakiyem.surucu.proje.model.login

import com.google.gson.annotations.SerializedName

data class Response4Login(

    @SerializedName("Detay")
    var detay: String? = null,
    @SerializedName("KursiyerId")
    var kursiyerId: String? = null,
    @SerializedName("KursiyerKey")
    var kursiyerKey: String? = null,
    @SerializedName("GrupId")
    var grupId: String? = null,
    @SerializedName("AdSoyad")
    var adSoyad: String? = null,
    @SerializedName("TcKimlik")
    var tckn: String? = null,

)