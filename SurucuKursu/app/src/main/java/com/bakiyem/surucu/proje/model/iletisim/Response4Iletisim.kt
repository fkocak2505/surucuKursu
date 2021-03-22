package com.bakiyem.surucu.proje.model.iletisim

import com.google.gson.annotations.SerializedName

data class Response4Iletisim(

	@field:SerializedName("Gsm")
	val gsm: String? = null,

	@field:SerializedName("HaritaY")
	val haritaY: String? = null,

	@field:SerializedName("HaritaX")
	val haritaX: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null,

	@field:SerializedName("KursAdi")
	val kursAdi: String? = null,

	@field:SerializedName("Twiter")
	val twiter: String? = null,

	@field:SerializedName("Logo")
	val logo: String? = null,

	@field:SerializedName("Bakiyem")
	val bakiyem: String? = null,

	@field:SerializedName("Mail")
	val mail: String? = null,

	@field:SerializedName("Telefon1")
	val telefon1: String? = null,

	@field:SerializedName("Telefon2")
	val telefon2: String? = null,

	@field:SerializedName("Web")
	val web: String? = null,

	@field:SerializedName("Renk")
	val renk: String? = null,

	@field:SerializedName("Instagram")
	val instagram: String? = null,

	@field:SerializedName("Facebook")
	val facebook: String? = null,

	@field:SerializedName("Adres")
	val adres: String? = null,

	@field:SerializedName("Whatsapp")
	val whatsapp: String? = null
)
