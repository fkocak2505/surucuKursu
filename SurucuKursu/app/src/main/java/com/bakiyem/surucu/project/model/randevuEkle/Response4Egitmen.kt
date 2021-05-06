package com.bakiyem.surucu.project.model.randevuEkle

import com.google.gson.annotations.SerializedName

data class Response4Egitmen(

	@field:SerializedName("AdSoyad")
	val adSoyad: String? = null,

	@field:SerializedName("Detay")
	val detay: String? = null,

	@field:SerializedName("Gorevi")
	val gorevi: String? = null,

	@field:SerializedName("Resim")
	val resim: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null
)
