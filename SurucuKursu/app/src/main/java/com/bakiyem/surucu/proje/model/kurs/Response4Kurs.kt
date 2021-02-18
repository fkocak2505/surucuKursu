package com.bakiyem.surucu.proje.model.kurs

import com.google.gson.annotations.SerializedName

data class Response4Kurs(

    @SerializedName("Keyi")
    var key: String? = null,

    @SerializedName("KursAdi")
    var kursAdi: String? = null,

    @SerializedName("Adres")
    var adres: String? = null,

    @SerializedName("Telefon1")
    var tel1: String? = null,

    @SerializedName("Telefon2")
    var tel2: String? = null,

    @SerializedName("Gsm")
    var gsm: String? = null,

    @SerializedName("Mail")
    var mail: String? = null,

    @SerializedName("Web")
    var web: String? = null,

    @SerializedName("Facebook")
    var facebook: String? = null,

    @SerializedName("Twiter")
    var twitter: String? = null,

    @SerializedName("Instagram")
    var instagram: String? = null,

    @SerializedName("Whatsapp")
    var whatsapp: String? = null,

    @SerializedName("Logo")
    var logo: String? = null,

    @SerializedName("HaritaX")
    var haritaX: String? = null,

    @SerializedName("HaritaY")
    var haritaY: String? = null,

    @SerializedName("Bakiyem")
    var bakiyem: String? = null,

    @SerializedName("Renk")
    var renk: String? = null,

)