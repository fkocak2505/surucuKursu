package com.bakiyem.surucu.project.model.odemeBilgilerim

import com.google.gson.annotations.SerializedName

data class Response4BorcOzet(

	@field:SerializedName("TahsilEdilen")
	val tahsilEdilen: String? = null,

	@field:SerializedName("KalanBorcu")
	val kalanBorcu: String? = null,

	@field:SerializedName("ToplamBorc")
	val toplamBorc: String? = null
)
