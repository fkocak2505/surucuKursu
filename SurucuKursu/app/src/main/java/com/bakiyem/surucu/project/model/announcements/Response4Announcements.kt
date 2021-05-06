package com.bakiyem.surucu.project.model.announcements

import com.google.gson.annotations.SerializedName

data class Response4Announcements(

	@SerializedName("Baslik")
	val baslik: String? = null,

	@SerializedName("Resim")
	val resim: String? = null,

	@SerializedName("Keyi")
	val keyi: String? = null,

	@SerializedName("Tarih")
	val tarih: String? = null
)