package com.bakiyem.surucu.project.model.derslerim

import com.google.gson.annotations.SerializedName

data class Response4Derslerim(

    @SerializedName("Id")
    var id: String? = null,

    @SerializedName("DersAdTr")
    var dersAdi: String? = null,

    @SerializedName("imageUrl")
    var imageUrl: String? = null,

    @SerializedName("iconUrl")
    var iconUrl: String? = null

)