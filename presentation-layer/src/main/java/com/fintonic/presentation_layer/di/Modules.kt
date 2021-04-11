package com.fintonic.presentation_layer.di

import com.fintonic.domain_layer.base.BaseDomainLayerBridge
import com.fintonic.domain_layer.feature.BEERS_BRIDGE_TAG
import com.fintonic.presentation_layer.viewmodels.BeersActivityViewModel
import com.fintonic.presentation_layer.viewmodels.BeersListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationLayerModule = module(override = true) {

    
    viewModel { BeersActivityViewModel(bridge = BaseDomainLayerBridge.None) }
    viewModel {
        BeersListViewModel(
            bridge = get(named(name = BEERS_BRIDGE_TAG))
        )
    }
}