package com.bakiyem.surucu.proje.model.derslerim

import com.google.gson.annotations.SerializedName

data class Response4Derslerim(

    @SerializedName("Id")
    var id: String? = null,

    @SerializedName("DersAdTr")
    var dersAdi: String? = null

)