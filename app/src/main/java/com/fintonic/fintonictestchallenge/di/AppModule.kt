package com.fintonic.fintonictestchallenge.di

import com.fintonic.fintonictestchallenge.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
    single(named("BASE_URL")) {
        BuildConfig.BASE_URL
    }
}