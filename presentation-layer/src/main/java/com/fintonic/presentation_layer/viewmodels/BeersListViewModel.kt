package com.fintonic.presentation_layer.viewmodels

import androidx.lifecycle.viewModelScope
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.domain.FailureBo
import com.fintonic.domain_layer.feature.BeersDomainLayerBridge
import com.fintonic.presentation_layer.base.BaseMvvmViewModel
import com.fintonic.presentation_layer.screen_state.BeersListState


class BeersListViewModel(
    bridge: BeersDomainLayerBridge<List<Beer>>
) : BaseMvvmViewModel<BeersDomainLayerBridge<List<Beer>>, BeersListState>(bridge) {


    fun onViewCreated() {
        fetchBeersList()
    }

    private fun fetchBeersList() {
        bridge.fetchBeersList(
            scope = viewModelScope,
            onResult = {
                it.fold(::handleError, ::handleSuccess)
            }
        )
    }

    private fun handleSuccess(beerList: List<Beer>) {
        beerList
        //TODO ADD SUCCESS

    }

    private fun handleError(failureBo: FailureBo) {
        //   _screenState.value =
        //       ScreenState.Render(BeersListState.ShowError(failure = failureBo.boToVoFailure()))
    }
}
