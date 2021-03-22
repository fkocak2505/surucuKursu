package com.bakiyem.surucu.proje.model.randevularim

import com.google.gson.annotations.SerializedName

data class Response4Randevularim(

	@field:SerializedName("SaatBitis")
	val saatBitis: String? = null,

	@field:SerializedName("SaatBaslama")
	val saatBaslama: String? = null,

	@field:SerializedName("Egitmen")
	val egitmen: String? = null,

	@field:SerializedName("Tarih")
	val tarih: String? = null,

	@field:SerializedName("Arac")
	val arac: Any? = null,

	@field:SerializedName("Durum")
	val durum: String? = null
)
