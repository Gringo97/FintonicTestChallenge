package com.fintonic.presentation_layer.screen_state

import com.fintonic.presentation_layer.base.BaseState
import com.fintonic.presentation_layer.domain.FailureVo

sealed class BeersListState : BaseState() {
    object Loading : BeersListState()
    object Idle : BeersListState()
    class ShowError(val failure: FailureVo?) : BeersListState()
}