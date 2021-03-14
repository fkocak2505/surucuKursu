package com.bakiyem.surucu.proje

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class SurucuKursuApplication: Application() {

    companion object{
        private var instance: SurucuKursuApplication? = null

        fun getInstance(): SurucuKursuApplication? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Fresco.initialize(this)

    }


}