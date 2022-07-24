package com.example.contextualcards

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ContextualCardsApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(applicationContext)

    }
}