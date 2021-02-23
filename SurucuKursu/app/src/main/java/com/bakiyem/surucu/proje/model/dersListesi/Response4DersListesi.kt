package com.bakiyem.surucu.proje.model.dersListesi

import com.google.gson.annotations.SerializedName

data class Response4DersListesi(

    @SerializedName("Id")
    var id: String? = null,

    @SerializedName("Keyi")
    var keyi: String? = null,

    @SerializedName("KategoriId")
    var kategoriId: String? = null,

    @SerializedName("BaslikTr")
    var baslik: String? = null

)