package com.fintonic.presentation_layer.beer_list.viewmodels

import androidx.lifecycle.viewModelScope
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.domain.FailureBo
import com.fintonic.domain_layer.feature.BeersDomainLayerBridge
import com.fintonic.presentation_layer.base.BaseMvvmViewModel
import com.fintonic.presentation_layer.base.ScreenState
import com.fintonic.presentation_layer.beer_list.screen_state.BeersListState
import com.fintonic.presentation_layer.domain.BeerVo
import com.fintonic.presentation_layer.domain.boToVo
import com.fintonic.presentation_layer.domain.boToVoFailure


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
        _screenState.value =
            ScreenState.Render(BeersListState.LoadBeers(beerList = beerList.map {
                it.boToVo()
            }))
    }

    private fun handleError(failureBo: FailureBo) {
        _screenState.value =
            ScreenState.Render(BeersListState.ShowError(failure = failureBo.boToVoFailure()))
    }

    fun loadBeerDetail(beerVo: BeerVo) {
        //TODO
    }
}
