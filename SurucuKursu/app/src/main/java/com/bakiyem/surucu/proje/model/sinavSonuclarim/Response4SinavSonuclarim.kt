package com.bakiyem.surucu.proje.model.sinavSonuclarim

import com.google.gson.annotations.SerializedName

data class Response4SinavSonuclarim(

	@field:SerializedName("MotorBos")
	val motorBos: String? = null,

	@field:SerializedName("ToplamSoruTrafik")
	val toplamSoruTrafik: String? = null,

	@field:SerializedName("TrafikDogru")
	val trafikDogru: String? = null,

	@field:SerializedName("IlkYrdmBos")
	val ilkYrdmBos: String? = null,

	@field:SerializedName("TrafikYanlis")
	val trafikYanlis: String? = null,

	@field:SerializedName("AdapDogru")
	val adapDogru: String? = null,

	@field:SerializedName("ToplamSure")
	val toplamSure: String? = null,

	@field:SerializedName("AdapBos")
	val adapBos: String? = null,

	@field:SerializedName("ToplamYanlisSoru")
	val toplamYanlisSoru: String? = null,

	@field:SerializedName("MotorDogru")
	val motorDogru: String? = null,

	@field:SerializedName("MotorYanlis")
	val motorYanlis: String? = null,

	@field:SerializedName("AdapYanlis")
	val adapYanlis: String? = null,

	@field:SerializedName("ToplamSoru")
	val toplamSoru: String? = null,

	@field:SerializedName("ToplamDogruSoru")
	val toplamDogruSoru: String? = null,

	@field:SerializedName("IlkYrdmYanlis")
	val ilkYrdmYanlis: String? = null,

	@field:SerializedName("ToplamSoruMotor")
	val toplamSoruMotor: String? = null,

	@field:SerializedName("ToplamSoruAdap")
	val toplamSoruAdap: String? = null,

	@field:SerializedName("ToplamSinav")
	val toplamSinav: String? = null,

	@field:SerializedName("ToplamBosSoru")
	val toplamBosSoru: String? = null,

	@field:SerializedName("ToplamSoruIlkYrdm")
	val toplamSoruIlkYrdm: String? = null,

	@field:SerializedName("IlkYrdmDogru")
	val ilkYrdmDogru: String? = null,

	@field:SerializedName("TrafikBos")
	val trafikBos: String? = null
)
