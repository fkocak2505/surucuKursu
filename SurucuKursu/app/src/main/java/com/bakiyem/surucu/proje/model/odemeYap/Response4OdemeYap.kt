package com.bakiyem.surucu.proje.model.odemeYap

import com.google.gson.annotations.SerializedName

data class Response4OdemeYap(

    @SerializedName("link")
    val link: String? = null,

    @SerializedName("trxcode")
    val trxcode: String? = null

)