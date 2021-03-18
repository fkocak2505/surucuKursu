package com.bakiyem.surucu.proje.model.sinifSinavi

import com.google.gson.annotations.SerializedName

data class Response4SinifSinavi(

	@field:SerializedName("MotorBos")
	val motorBos: String? = null,

	@field:SerializedName("Sonuc")
	val sonuc: String? = null,

	@field:SerializedName("SinavId")
	val sinavId: String? = null,

	@field:SerializedName("TrafikDogru")
	val trafikDogru: String? = null,

	@field:SerializedName("IlkYrdmBos")
	val ilkYrdmBos: String? = null,

	@field:SerializedName("Keyi")
	val keyi: String? = null,

	@field:SerializedName("TrafikYanlis")
	val trafikYanlis: String? = null,

	@field:SerializedName("AdapDogru")
	val adapDogru: String? = null,

	@field:SerializedName("AdapBos")
	val adapBos: String? = null,

	@field:SerializedName("Sure")
	val sure: String? = null,

	@field:SerializedName("MotorDogru")
	val motorDogru: String? = null,

	@field:SerializedName("MotorYanlis")
	val motorYanlis: String? = null,

	@field:SerializedName("AdapYanlis")
	val adapYanlis: String? = null,

	@field:SerializedName("Puan")
	val puan: String? = null,

	@field:SerializedName("Baslik")
	val baslik: String? = null,

	@field:SerializedName("IlkYrdmYanlis")
	val ilkYrdmYanlis: String? = null,

	@field:SerializedName("IlkYrdmDogru")
	val ilkYrdmDogru: String? = null,

	@field:SerializedName("TrafikBos")
	val trafikBos: String? = null
)
