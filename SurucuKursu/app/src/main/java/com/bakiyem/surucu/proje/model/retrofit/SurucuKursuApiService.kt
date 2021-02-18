package com.bakiyem.surucu.proje.model.retrofit

import com.bakiyem.surucu.proje.base.model.ResResult
import com.bakiyem.surucu.proje.base.model.ResResultArray
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
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
        return api.getAnnouncements(tokenBody, kursiyerBody, grup)
    }

    fun getDuyuruDetay(key: String): Observable<ResResultArray<Response4DuyuruDetay>> {
        val token = Hawk.get<String>("token")

        val tokenBody = RequestBody.create(MediaType.parse("text/plain"), token)
        val duyuruBody = RequestBody.create(MediaType.parse("text/plain"), key)

        return api.getDuyuruDetay(tokenBody, duyuruBody)

    }

    /*fun getAnnouncements(): Single<Response<Response4Announcements>> {
        val tokenBody = RequestBody.create(
            MediaType.parse("text/plain"),
            "7CDC9E4213DDD7B5044B39A9E5F35F8A"
        )
        val kursiyerBody = RequestBody.create(MediaType.parse("text/plain"), "2")
        val grup = RequestBody.create(MediaType.parse("text/plain"), "4")


        return api.getAnnouncements(tokenBody,kursiyerBody,grup)
    }*/

}