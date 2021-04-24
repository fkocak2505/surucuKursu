package com.bakiyem.surucu.proje.model.video

import com.google.gson.annotations.SerializedName

data class Response4Video(

	@field:SerializedName("Baslik")
	val baslik: String? = null,

	@field:SerializedName("Resim")
	val resim: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null,

	@field:SerializedName("Link")
	val link: String? = null
)
