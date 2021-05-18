package com.fintonic.presentation_layer.beer_list.screen_state

import com.fintonic.presentation_layer.base.BaseState
import com.fintonic.presentation_layer.domain.BeerVo
import com.fintonic.presentation_layer.domain.FailureVo

sealed class BeersListState : BaseState() {
    class LoadBeers(val beerList : List<BeerVo>) : BeersListState()
    class ShowError(val failure: FailureVo?) : BeersListState()

}