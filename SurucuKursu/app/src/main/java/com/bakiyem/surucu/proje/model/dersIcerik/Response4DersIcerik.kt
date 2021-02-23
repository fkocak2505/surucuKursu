package com.bakiyem.surucu.proje.model.dersIcerik

import com.google.gson.annotations.SerializedName

data class Response4DersIcerik(

    @SerializedName("Baslik")
    var baslik: String? = null,

    @SerializedName("Detay")
    var detay: String? = null,

    @SerializedName("SesDosyasi")
    var sesDosyasi: String? = null

)