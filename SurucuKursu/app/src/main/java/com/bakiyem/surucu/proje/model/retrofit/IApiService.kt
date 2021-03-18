package com.bakiyem.surucu.proje.model.retrofit

import com.bakiyem.surucu.proje.base.model.ResResult
import com.bakiyem.surucu.proje.base.model.ResResultArray
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4SinavSonuc
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.model.profilim.Response4Profilim
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface IApiService {

    @Multipart
    @POST("/KursumAPI/kurs/index.php")
    fun getKurs(
        @Part("token") token: RequestBody,
    ): Observable<ResResult<Response4Kurs>>

    @Multipart
    @POST("/KursumAPI/duyurular/index.php")
    fun getAnnouncements(
        @Part("token") token: RequestBody,
        @Part("kursiyer") kursiyer: RequestBody,
        @Part("grup") grup: RequestBody
    ): Observable<ResResultArray<Response4Announcements>>

    @Multipart
    @POST("/KursumAPI/duyuru-detay/index.php")
    fun getDuyuruDetay(
        @Part("token") token: RequestBody,
        @Part("duyuru") kursiyer: RequestBody
    ): Observable<ResResultArray<Response4DuyuruDetay>>

    @Multipart
    @POST("/KursumAPI/giris/index.php")
    fun doLogin(
        @Part("token") token: RequestBody,
        @Part("Fcm") fcm: RequestBody,
        @Part("Cihaz") cihaz: RequestBody,
        @Part("TCno") tckn: RequestBody
    ): Observable<ResResult<Response4Login>>

    @Multipart
    @POST("/KursumAPI/hakkinda/index.php")
    fun getHakkimizda(
        @Part("token") token: RequestBody,
    ): Observable<ResResult<Response4Hakkimizda>>

    @Multipart
    @POST("/KursumAPI/araclar/index.php")
    fun getAraclar(
        @Part("token") token: RequestBody,
    ): Observable<ResResultArray<Response4Araclar>>

    @Multipart
    @POST("/KursumAPI/galeri/index.php")
    fun getGaleri(
        @Part("token") token: RequestBody,
    ): Observable<ResResultArray<Response4Galeri>>

    @Multipart
    @POST("/KursumAPI/kadro/index.php")
    fun getKadromuz(
        @Part("token") token: RequestBody,
    ): Observable<ResResultArray<Response4Kadromuz>>

    @Multipart
    @POST("/KursumAPI/kursiyer/index.php")
    fun getProfilim(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") kursiyerKey: RequestBody
    ): Observable<ResResult<Response4Profilim>>

    @Multipart
    @POST("/KursumAPI/dersgruplar/index.php")
    fun getDersKategori(
        @Part("token") token: RequestBody,
    ): Observable<ResResultArray<Response4Derslerim>>

    @Multipart
    @POST("/KursumAPI/dersler/index.php")
    fun getDersListesi(
        @Part("token") token: RequestBody,
        @Part("kategori") kategori: RequestBody
    ): Observable<ResResultArray<Response4DersListesi>>

    @Multipart
    @POST("/KursumAPI/ders-icerik/index.php")
    fun getDersIcerik(
        @Part("token") token: RequestBody,
        @Part("derskey") derskey: RequestBody
    ): Observable<ResResultArray<Response4DersIcerik>>

    @Multipart
    @POST("/KursumAPI/deneme-sinavlar/index.php")
    fun getDenemeSinavi(
        @Part("token") token: RequestBody
    ): Observable<ResResultArray<Response4DenemeSinavi>>

    @Multipart
    @POST("/KursumAPI/sonuc-gonder/index.php")
    fun postSinavSonuc(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") kursiyerKey: RequestBody,
        @Part("Tur") tur: RequestBody,
        @Part("IlkYrdmDogru") ilkYrdmDogru: RequestBody,
        @Part("IlkYrdmYanlis") ilkYrdmYanlis: RequestBody,
        @Part("IlkYrdmBos") ilkYrdmBos: RequestBody,
        @Part("TrafikDogru") trafikDogru: RequestBody,
        @Part("TrafikYanlis") trafikYanlis: RequestBody,
        @Part("TrafikBos") trafikBos: RequestBody,
        @Part("MotorDogru") motorDogru: RequestBody,
        @Part("MotorYanlis") motorYanlis: RequestBody,
        @Part("MotorBos") motorBos: RequestBody,
        @Part("AdapDogru") adapDogru: RequestBody,
        @Part("AdapYanlis") adapYanlis: RequestBody,
        @Part("AdapBos") adapBos: RequestBody,
        @Part("Sinav") sinav: RequestBody,
        @Part("Sure") sure: RequestBody
    ): Observable<ResResult<Response4SinavSonuc>>

    @Multipart
    @POST("/KursumAPI/ozel-sinavlar/index.php")
    fun getOzelSinav(
        @Part("token") token: RequestBody,
        @Part("IlkYardimSoru") ilkYardimSoru: RequestBody,
        @Part("TrafikSoru") trafikSoru: RequestBody,
        @Part("MotorSoru") motorSoru: RequestBody,
        @Part("AdapSoru") adapSoru: RequestBody,
    ): Observable<ResResultArray<Response4DenemeSinavi>>

    @Multipart
    @POST("/KursumAPI/kursiyer-sinavlar/index.php")
    fun getSinifSinavi(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") kursiyerKey: RequestBody
    ): Observable<ResResultArray<Response4SinifSinavi>>

    @Multipart
    @POST("/KursumAPI/sinif-sinavlar/index.php")
    fun getSinifSinaviQuiz(
        @Part("token") token: RequestBody,
        @Part("sinav") sinavKey: RequestBody
    ): Observable<ResResultArray<Response4DenemeSinavi>>


}