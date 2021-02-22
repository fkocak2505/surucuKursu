package com.bakiyem.surucu.proje.model.retrofit

import com.bakiyem.surucu.proje.base.model.ResResult
import com.bakiyem.surucu.proje.base.model.ResResultArray
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.login.Response4Login
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


}