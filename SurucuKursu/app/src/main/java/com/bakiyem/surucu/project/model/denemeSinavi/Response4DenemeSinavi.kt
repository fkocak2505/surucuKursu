package com.bakiyem.surucu.project.model.denemeSinavi

import com.google.gson.annotations.SerializedName

data class Response4DenemeSinavi(

	@field:SerializedName("Kategori")
	val kategori: String? = null,

	@field:SerializedName("SoruResim")
	val soruResim: String? = null,

	@field:SerializedName("Secenekler")
	val secenekler: List<SeceneklerItem?>? = null,

	@field:SerializedName("Soru")
	val soru: String? = null,

	@field:SerializedName("SoruId")
	val soruId: String? = null,

	@field:SerializedName("SoruAciklama")
	val soruAciklama: String? = null
)

data class SeceneklerItem(

	@field:SerializedName("CevapFoto")
	val cevapFoto: String? = null,

	@field:SerializedName("Cevap")
	val cevap: String? = null,

	@field:SerializedName("Dogru")
	val dogru: String? = null
)
