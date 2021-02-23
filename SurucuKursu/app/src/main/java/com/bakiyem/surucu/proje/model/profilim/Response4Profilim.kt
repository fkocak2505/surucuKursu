package com.bakiyem.surucu.proje.model.profilim

import com.google.gson.annotations.SerializedName

data class Response4Profilim(

    @SerializedName("Id")
    var id: String?= null,

    @SerializedName("Keyi")
    var keyi: String?= null,

    @SerializedName("KursId")
    var kursId: String?= null,

    @SerializedName("KursKey")
    var kursKey: String?= null,

    @SerializedName("AdayNo")
    var adayNo: String?= null,

    @SerializedName("Ad")
    var ad: String?= null,

    @SerializedName("Soyad")
    var soyad: String?= null,

    @SerializedName("GrupId")
    var grupId: String?= null,

    @SerializedName("Grup")
    var grup: String?= null,

    @SerializedName("KayitTarihi")
    var kayitTarihi: String?= null,

    @SerializedName("SertifikaSinif")
    var sertifikaSinif: String?= null,

    @SerializedName("Dili")
    var dili: String?= null,

    @SerializedName("Cinsiyeti")
    var cinsiyeti: String?= null,

    @SerializedName("KanGrup")
    var kanGrup: String?= null,

    @SerializedName("OnlineDers")
    var onlineDers: String?= null,

    @SerializedName("Durumu")
    var durumu: String?= null,

    @SerializedName("Dil")
    var dil: String?= null,

    @SerializedName("Adres")
    var adres: String?= null,

    @SerializedName("EvTelefonu")
    var evTel: String?= null,

    @SerializedName("IsTelefonu")
    var isTel: String?= null,

    @SerializedName("Gsm")
    var gsm: String?= null,

    @SerializedName("AcilErisimTel")
    var acilTel: String?= null,

    @SerializedName("Mail")
    var mail: String?= null,

    @SerializedName("TcKimlik")
    var tckn: String?= null,

    @SerializedName("DogumTarihi")
    var dogumTarih: String?= null,

    @SerializedName("Cinsiyet")
    var cinsiyet: String?= null,

    @SerializedName("KanGrubu")
    var kanGrubu: String?= null,

    @SerializedName("Resim")
    var resim: String?= null
)

