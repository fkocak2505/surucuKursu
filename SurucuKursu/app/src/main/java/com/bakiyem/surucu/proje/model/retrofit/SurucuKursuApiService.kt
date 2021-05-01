package com.bakiyem.surucu.proje.model.retrofit

import com.bakiyem.surucu.proje.base.model.ResResult
import com.bakiyem.surucu.proje.base.model.ResResultArray
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.bakiyem.surucu.proje.model.announcements.Response4Slider
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4SinavSonuc
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.model.faydaliBilgiler.Response4FaydaliBilgiler
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.model.iletisim.Response4Iletisim
import com.bakiyem.surucu.proje.model.iletisim.Response4SendFeedback
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.proje.model.odemeYap.Response4OdemeYap
import com.bakiyem.surucu.proje.model.profilim.Response4Profilim
import com.bakiyem.surucu.proje.model.randevuEkle.Response4Egitmen
import com.bakiyem.surucu.proje.model.randevuSaat.Response4Saat
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.proje.model.video.Response4Video
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class SurucuKursuApiService {

    private val BASE_URL = "https://pos.bakiyem.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(IApiService::class.java)

    fun getKurs(): Observable<ResResult<Response4Kurs>> {
        val tokenBody =
            RequestBody.create(MediaType.parse("text/plain"), "7CDC9E4213DDD7B5044B39A9E5F35F8A")
        return api.getKurs(tokenBody)
    }

    fun getAnnouncements(): Observable<ResResultArray<Response4Announcements>> {

        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), "2")
        val grup = RequestBody.create(MediaType.parse("text/plain"), "4")

        val loginData = Hawk.get<Response4Login>("loginResponse", null)
        loginData?.let {
            return api.getAnnouncements(tokenBody, kursiyerBody, grup)
        }?: run{
            return api.getAnnouncements(tokenBody, null, null)
        }
    }

    fun getSlider(): Observable<ResResultArray<Response4Slider>>  {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getSlider(tokenBody)
    }

    fun getDuyuruDetay(key: String): Observable<ResResultArray<Response4DuyuruDetay>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val duyuruBody = RequestBody.create(MediaType.parse("text/plain"), key)

        return api.getDuyuruDetay(tokenBody, duyuruBody)

    }

    fun doLogin(tcNo: String): Observable<ResResult<Response4Login>> {
        val token = Hawk.get<String>("token")
        val fcmToken = Hawk.get("fcmToken", "1213213")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val fcmBody = RequestBody.create(MediaType.parse("text/plain"), fcmToken)
        val cihazBody = RequestBody.create(MediaType.parse("text/plain"), "2")
        val tcknBody = RequestBody.create(MediaType.parse("text/plain"), tcNo)

        return api.doLogin(tokenBody, fcmBody, cihazBody, tcknBody)

    }

    fun getHakkimizda(): Observable<ResResult<Response4Hakkimizda>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getHakkimizda(tokenBody)
    }

    fun getAraclar(): Observable<ResResultArray<Response4Araclar>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getAraclar(tokenBody)
    }

    fun getGaleri(): Observable<ResResultArray<Response4Galeri>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getGaleri(tokenBody)
    }

    fun getKadromuz(): Observable<ResResultArray<Response4Kadromuz>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getKadromuz(tokenBody)
    }

    fun getProfilim(): Observable<ResResult<Response4Profilim>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)

        return api.getProfilim(tokenBody, kursiyerBody)
    }

    fun getDersKategori(): Observable<ResResultArray<Response4Derslerim>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getDersKategori(tokenBody)
    }

    fun getDersListesi(kategoriId: String): Observable<ResResultArray<Response4DersListesi>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kategori = RequestBody.create(MediaType.parse("text/plain"), kategoriId)

        return api.getDersListesi(tokenBody, kategori)
    }

    fun getDersIcerik(dersKeyId: String): Observable<ResResultArray<Response4DersIcerik>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val dersKey = RequestBody.create(MediaType.parse("text/plain"), dersKeyId)

        return api.getDersIcerik(tokenBody, dersKey)
    }

    fun getDenemeSinavi(): Observable<ResResultArray<Response4DenemeSinavi>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getDenemeSinavi(tokenBody)
    }

    fun sendhataliSoru(soruId: String): Observable<ResResult<Response4SendFeedback>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        val soruBody = RequestBody.create(MediaType.parse("text/plain"), soruId)

        return api.sendhataliSoru(tokenBody,kursiyerBody, soruBody)
    }

    fun postSinavSonuc(
        aTur: String,
        iyDogru: String,
        iyYanlis: String,
        iyBos: String,
        tDogru: String,
        tYanlis: String,
        tBos: String,
        mDogru: String,
        mYanlis: String,
        mBos: String,
        aDogru: String,
        aYanlis: String,
        aBos: String,
        aSinav: String? = "",
        sure: String
    ): Observable<ResResult<Response4SinavSonuc>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)


        val turBody = RequestBody.create(MediaType.parse("text/plain"), aTur)
        val iyDogruBody = RequestBody.create(MediaType.parse("text/plain"), iyDogru)
        val iyYanlisBody = RequestBody.create(MediaType.parse("text/plain"), iyYanlis)
        val iyBosBody = RequestBody.create(MediaType.parse("text/plain"), iyBos)
        val tDogruBody = RequestBody.create(MediaType.parse("text/plain"), tDogru)
        val tYanlisBody = RequestBody.create(MediaType.parse("text/plain"), tYanlis)
        val tBosBody = RequestBody.create(MediaType.parse("text/plain"), tBos)
        val mDogruBody = RequestBody.create(MediaType.parse("text/plain"), mDogru)
        val mYanlisBody = RequestBody.create(MediaType.parse("text/plain"), mYanlis)
        val mBosBody = RequestBody.create(MediaType.parse("text/plain"), mBos)
        val aDogruBody = RequestBody.create(MediaType.parse("text/plain"), aDogru)
        val aYanlisBody = RequestBody.create(MediaType.parse("text/plain"), aYanlis)
        val aBosBody = RequestBody.create(MediaType.parse("text/plain"), aBos)
        val sinavBody = RequestBody.create(MediaType.parse("text/plain"), aSinav)
        val sureBody = RequestBody.create(MediaType.parse("text/plain"), sure)

        return api.postSinavSonuc(
            tokenBody,
            kursiyerBody,
            turBody,
            iyDogruBody,
            iyYanlisBody,
            iyBosBody,
            tDogruBody,
            tYanlisBody,
            tBosBody,
            mDogruBody,
            mYanlisBody,
            mBosBody,
            aDogruBody,
            aYanlisBody,
            aBosBody,
            sinavBody,
            sureBody
        )
    }

    fun getOzelSinav(
        ilkYardimCount: String,
        trafikCount: String,
        motorCount: String,
        adapCount: String
    ): Observable<ResResultArray<Response4DenemeSinavi>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val ilkYardimCountBody = RequestBody.create(MediaType.parse("text/plain"), ilkYardimCount)
        val trafikCountBody = RequestBody.create(MediaType.parse("text/plain"), trafikCount)
        val motorCountBody = RequestBody.create(MediaType.parse("text/plain"), motorCount)
        val adapCountBody = RequestBody.create(MediaType.parse("text/plain"), adapCount)

        return api.getOzelSinav(
            tokenBody,
            ilkYardimCountBody,
            trafikCountBody,
            motorCountBody,
            adapCountBody
        )
    }

    fun getSinifSinavi(): Observable<ResResultArray<Response4SinifSinavi>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)

        return api.getSinifSinavi(tokenBody, kursiyerBody)
    }

    fun getGirilenSinav(): Observable<ResResultArray<Response4SinifSinavi>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)

        return api.getGirilenSinav(tokenBody, kursiyerBody)
    }



    fun getSinifSinaviQuiz(sinavId: String): Observable<ResResultArray<Response4DenemeSinavi>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val sinavBody = RequestBody.create(MediaType.parse("text/plain"), sinavId)

        return api.getSinifSinaviQuiz(tokenBody, sinavBody)
    }

    fun getSinavSonuclarim(): Observable<ResResult<Response4SinavSonuclarim>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        return api.getSinavSonuclarim(tokenBody, kursiyerBody)
    }

    fun getBorcListesi(): Observable<ResResultArray<Response4OdemeBilgileri>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        return api.getBorcListesi(tokenBody, kursiyerBody)
    }

    fun getBorcOzet(): Observable<ResResult<Response4BorcOzet>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        return api.getBorcOzet(tokenBody, kursiyerBody)
    }

    fun getRandevularim(): Observable<ResResultArray<Response4Randevularim>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        return api.getRandevularim(tokenBody, kursiyerBody)
    }

    fun getFaydaliBilgiler(): Observable<ResResultArray<Response4FaydaliBilgiler>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        return api.getFaydaliBilgiler(tokenBody)
    }

    fun getIletisim(): Observable<ResResult<Response4Iletisim>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        return api.getIletisim(tokenBody)
    }

    fun sendFeedback(
        adSoyad: String,
        mail: String,
        tel: String,
        mesaj: String
    ): Observable<ResResult<Response4SendFeedback>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val adSoyadBody = RequestBody.create(MediaType.parse("text/plain"), adSoyad)
        val mailBody = RequestBody.create(MediaType.parse("text/plain"), mail)
        val telBody = RequestBody.create(MediaType.parse("text/plain"), tel)
        val mesajBody = RequestBody.create(MediaType.parse("text/plain"), mesaj)

        return api.sendFeedback(tokenBody, adSoyadBody, mailBody, telBody, mesajBody)
    }

    fun randevuIptal(randevuId: String): Observable<ResResult<Response4SendFeedback>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        val randevuIdBody = RequestBody.create(MediaType.parse("text/plain"), randevuId)

        return api.randevuIptal(tokenBody, kursiyerBody, randevuIdBody)
    }

    fun getRandevuEgitmenList(tarih: String): Observable<ResResultArray<Response4Egitmen>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val tarihBody = RequestBody.create(MediaType.parse("text/plain"), tarih)
        return api.getRandevuEgitmenList(tokenBody, tarihBody)
    }

    fun getRandevuEgitmenSaat(
        tarih: String,
        egitmenId: String
    ): Observable<ResResultArray<Response4Saat>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val tarihBody = RequestBody.create(MediaType.parse("text/plain"), tarih)
        val egitmenIdBody = RequestBody.create(MediaType.parse("text/plain"), egitmenId)
        return api.getRandevuEgitmenSaat(tokenBody, tarihBody, egitmenIdBody)
    }

    fun addRandevu(
        tarih: String,
        egitmenId: String,
        saat: String,
        saatTxt: String
    ): Observable<ResResult<Response4SendFeedback>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)
        val tarihBody = RequestBody.create(MediaType.parse("text/plain"), tarih)
        val egitmenIdBody = RequestBody.create(MediaType.parse("text/plain"), egitmenId)
        val saatBody = RequestBody.create(MediaType.parse("text/plain"), saat)
        val saatTxtBody = RequestBody.create(MediaType.parse("text/plain"), saatTxt)
        return api.addRandevu(tokenBody, kursiyerBody, tarihBody, egitmenIdBody, saatBody,saatTxtBody)
    }

    fun odemeYap(
        kartNo: String,
        kartIsim: String,
        ay: String,
        yil: String,
        cv2: String,
        tutar: String
    ): Observable<ResResult<Response4OdemeYap>> {
        val token = Hawk.get<String>("token")
        val kursiyerKey = Hawk.get<Response4Login>("loginResponse").kursiyerKey!!

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), kursiyerKey)

        val kartNoBody = RequestBody.create(MediaType.parse("text/plain"), kartNo)
        val kartIsimBody = RequestBody.create(MediaType.parse("text/plain"), kartIsim)
        val ayBody = RequestBody.create(MediaType.parse("text/plain"), ay)
        val yilBody = RequestBody.create(MediaType.parse("text/plain"), yil)
        val cv2Body = RequestBody.create(MediaType.parse("text/plain"), cv2)
        val tutarBody = RequestBody.create(MediaType.parse("text/plain"), tutar)

        return api.odemeYap(
            tokenBody,
            kursiyerBody,
            kartNoBody,
            kartIsimBody,
            ayBody,
            yilBody,
            cv2Body,
            tutarBody
        )
    }

    fun getVideolar(): Observable<ResResultArray<Response4Video>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)

        return api.getVideolar(tokenBody)
    }


}