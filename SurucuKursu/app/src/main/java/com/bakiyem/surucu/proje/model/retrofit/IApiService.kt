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
        @Part("kursiyer") kursiyer: RequestBody?,
        @Part("grup") grup: RequestBody?
    ): Observable<ResResultArray<Response4Announcements>>

    @Multipart
    @POST("/KursumAPI/slide/index.php")
    fun getSlider(
        @Part("token") token: RequestBody
    ): Observable<ResResultArray<Response4Slider>>

    @Multipart
    @POST("/KursumAPI/video/index.php")
    fun getVideolar(
        @Part("token") token: RequestBody
    ): Observable<ResResultArray<Response4Video>>



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
    @POST("/KursumAPI/hatali-soru/index.php")
    fun sendhataliSoru(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") kursiyerKey: RequestBody,
        @Part("soruId") soruId: RequestBody,
    ): Observable<ResResult<Response4SendFeedback>>


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

    @Multipart
    @POST("/KursumAPI/sonuc-ozet/index.php")
    fun getSinavSonuclarim(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody
    ): Observable<ResResult<Response4SinavSonuclarim>>

    @Multipart
    @POST("/KursumAPI/kursiyer-borclar/index.php")
    fun getBorcListesi(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody
    ): Observable<ResResultArray<Response4OdemeBilgileri>>

    @Multipart
    @POST("/KursumAPI/kursiyer-borc-ozet/index.php")
    fun getBorcOzet(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody
    ): Observable<ResResult<Response4BorcOzet>>

    @Multipart
    @POST("/KursumAPI/kursiyer-randevu/index.php")
    fun getRandevularim(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody
    ): Observable<ResResultArray<Response4Randevularim>>

    @Multipart
    @POST("/KursumAPI/pratik/index.php")
    fun getFaydaliBilgiler(
        @Part("token") token: RequestBody
    ): Observable<ResResultArray<Response4FaydaliBilgiler>>

    @Multipart
    @POST("/KursumAPI/kurs/index.php")
    fun getIletisim(
        @Part("token") token: RequestBody
    ): Observable<ResResult<Response4Iletisim>>


    @Multipart
    @POST("/KursumAPI/basvuru/index.php")
    fun sendFeedback(
        @Part("token") token: RequestBody,
        @Part("AdSoyad") adSoyad: RequestBody,
        @Part("Mail") mail: RequestBody,
        @Part("Tel") tel: RequestBody,
        @Part("Mesaj") mesaj: RequestBody,
    ): Observable<ResResult<Response4SendFeedback>>

    @Multipart
    @POST("/KursumAPI/randevu-iptal/index.php")
    fun randevuIptal(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody,
        @Part("randevuId") randevuId: RequestBody
    ): Observable<ResResult<Response4SendFeedback>>


    @Multipart
    @POST("/KursumAPI/randevu-egitmen/index.php")
    fun getRandevuEgitmenList(
        @Part("token") token: RequestBody,
        @Part("tarih") tarih: RequestBody
    ): Observable<ResResultArray<Response4Egitmen>>

    @Multipart
    @POST("/KursumAPI/randevu-saatler/index.php")
    fun getRandevuEgitmenSaat(
        @Part("token") token: RequestBody,
        @Part("tarih") tarih: RequestBody,
        @Part("egitmen") egitmen: RequestBody
    ): Observable<ResResultArray<Response4Saat>>

    @Multipart
    @POST("/KursumAPI/randevu-ekle/index.php")
    fun addRandevu(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody,
        @Part("Tarih") tarih: RequestBody,
        @Part("PersonelId") egitmen: RequestBody,
        @Part("SaatId") saat: RequestBody
    ): Observable<ResResult<Response4SendFeedback>>

    @Multipart
    @POST("/KursumAPI/odeme-al/index.php")
    fun odemeYap(
        @Part("token") token: RequestBody,
        @Part("kursiyerKey") sinavKey: RequestBody,
        @Part("kart") kart: RequestBody,
        @Part("kart_isim") kart_isim: RequestBody,
        @Part("ay") ay: RequestBody,
        @Part("yil") yil: RequestBody,
        @Part("cv2") cv2: RequestBody,
        @Part("tutar") tutar: RequestBody
    ): Observable<ResResult<Response4OdemeYap>>







}