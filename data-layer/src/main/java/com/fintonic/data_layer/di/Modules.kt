package com.fintonic.data_layer.di

import com.fintonic.data_layer.domain.BeerDto
import com.fintonic.data_layer.remote.BeersRemoteDataSource
import com.fintonic.data_layer.remote.BeersRemoteDataSource.Companion.BASE_PUNK_API_URL
import com.fintonic.data_layer.remote.BeersRemoteDataSource.Companion.BEERS_REMOTE_DATA_SOURCE_TAG
import com.fintonic.data_layer.remote.BeersRemoteDataSource.Companion.RETROFIT_BEERS_TAG
import com.fintonic.data_layer.remote.BeersRemoteDataSource.Companion.RETROFIT_MOSHI_TAG
import com.fintonic.data_layer.remote.BeersRemoteDataSourceImpl
import com.fintonic.data_layer.repository.Repository
import com.fintonic.domain_layer.DomainLayerContract
import com.fintonic.domain_layer.DomainLayerContract.DataLayer.Companion.BEER_LIST_REPOSITORY_TAG
import com.fintonic.domain_layer.domain.Beer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory




val dataLayerModule = module(override = true) {


    // repository
    single {
        Repository.apply {
            beersDataSource = get(named(name = BEERS_REMOTE_DATA_SOURCE_TAG))
        }
    }


    single<DomainLayerContract.DataLayer.BeersListRepository<List<Beer>>>(named(name = BEER_LIST_REPOSITORY_TAG)) {
        get<Repository>()
    }


    // data-source

    factory<BeersRemoteDataSource>(named(name = BEERS_REMOTE_DATA_SOURCE_TAG)) {
        BeersRemoteDataSourceImpl(get(named(name = RETROFIT_MOSHI_TAG)))
    }



    single<Retrofit>(named(name = RETROFIT_MOSHI_TAG)) {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_PUNK_API_URL)
            .build()
    }



}