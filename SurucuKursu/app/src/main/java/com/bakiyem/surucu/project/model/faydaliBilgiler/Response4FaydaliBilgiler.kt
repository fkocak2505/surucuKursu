package com.bakiyem.surucu.project.model.faydaliBilgiler

import com.google.gson.annotations.SerializedName

data class Response4FaydaliBilgiler(

	@field:SerializedName("Baslik")
	val baslik: String? = null,

	@field:SerializedName("Detay")
	val detay: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null
)
