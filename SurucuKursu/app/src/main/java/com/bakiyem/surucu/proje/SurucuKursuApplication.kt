package com.bakiyem.surucu.proje

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class SurucuKursuApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

    }
}