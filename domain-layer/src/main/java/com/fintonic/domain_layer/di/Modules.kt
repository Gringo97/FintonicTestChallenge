package com.fintonic.domain_layer.di

import arrow.core.None
import com.fintonic.domain_layer.DomainLayerContract
import com.fintonic.domain_layer.DomainLayerContract.DataLayer.Companion.BEER_LIST_REPOSITORY_TAG
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.feature.BEERS_BRIDGE_TAG
import com.fintonic.domain_layer.feature.BeersDomainLayerBridge
import com.fintonic.domain_layer.feature.BeersDomainLayerBridgeImpl
import com.fintonic.domain_layer.usecase.FETCH_BEERS_LIST_UC_TAG
import com.fintonic.domain_layer.usecase.FetchBeersListUc
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * This variable represents the 'data-layer' dependencies module to be used by Koin. It basically
 * includes repository and data-source definitions.
 */
val domainLayerModule = module(override = true) {

    //bridge

    factory<BeersDomainLayerBridge<List<Beer>>>(named(name = BEERS_BRIDGE_TAG)) {
        BeersDomainLayerBridgeImpl(
            fetchBeersListUc = get(named(name = FETCH_BEERS_LIST_UC_TAG))
        )
    }
    // use-case

    factory<DomainLayerContract.PresentationLayer.UseCase<Nothing, List<Beer>>>(named(name = FETCH_BEERS_LIST_UC_TAG)) {
        FetchBeersListUc(
            repository = get(
                named(name = BEER_LIST_REPOSITORY_TAG)
            )
        )
    }







}