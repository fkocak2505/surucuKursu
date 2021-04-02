package com.bakiyem.surucu.proje.model.randevuSaat

import com.google.gson.annotations.SerializedName

data class Response4Saat(

	@field:SerializedName("Saat")
	val saat: String? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@Transient
	var isSelect: Boolean? = false
)
