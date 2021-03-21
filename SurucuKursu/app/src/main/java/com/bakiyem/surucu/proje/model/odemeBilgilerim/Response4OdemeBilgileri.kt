package com.bakiyem.surucu.proje.model.odemeBilgilerim

import com.google.gson.annotations.SerializedName

data class Response4OdemeBilgileri(

	@field:SerializedName("Tutar")
	val tutar: String? = null,

	@field:SerializedName("Aciklama")
	val aciklama: String? = null,

	@field:SerializedName("OdemeTarih")
	val odemeTarih: String? = null,

	@field:SerializedName("MakbuzNo")
	val makbuzNo: String? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("BorcTarih")
	val borcTarih: String? = null,

	@field:SerializedName("Durum")
	val durum: String? = null,

	@field:SerializedName("OdemeTur")
	val odemeTur: String? = null
)
