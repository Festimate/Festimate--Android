package com.mtc.festimate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FestimateApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
