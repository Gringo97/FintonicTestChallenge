package com.fintonic.fintonictestchallenge

import android.app.Application
import com.fintonic.data_layer.di.dataLayerModule
import com.fintonic.domain_layer.di.domainLayerModule
import com.fintonic.presentation_layer.di.presentationLayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FintonicChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FintonicChallengeApp)
            modules(listOf(presentationLayerModule, domainLayerModule, dataLayerModule))
        }
    }




}