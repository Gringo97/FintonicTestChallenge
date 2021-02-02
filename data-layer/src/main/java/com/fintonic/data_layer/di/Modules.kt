package com.fintonic.data_layer.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val RETROFIT_MOSHI_TAG = "retrofitMoshi"

val dataLayerModule = module(override = true) {


    single<Retrofit>(named(name = RETROFIT_MOSHI_TAG)) {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                MoshiConverterFactory.create(Moshi.Builder().build())
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(get<String>(named("BASE_URL"))).build()
    }

}