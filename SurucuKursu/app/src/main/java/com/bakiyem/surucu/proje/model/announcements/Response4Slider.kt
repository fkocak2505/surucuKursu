package com.bakiyem.surucu.proje.model.announcements

import com.google.gson.annotations.SerializedName

data class Response4Slider(

	@field:SerializedName("Baslik")
	val baslik: String? = null,

	@field:SerializedName("Resim")
	val resim: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null,

	@field:SerializedName("AltBaslik")
	val altBaslik: String? = null
)
