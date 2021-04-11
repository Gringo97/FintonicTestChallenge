package com.fintonic.presentation_layer.viewmodels

import com.fintonic.domain_layer.base.BaseDomainLayerBridge
import com.fintonic.presentation_layer.base.BaseMvvmViewModel
import com.fintonic.presentation_layer.screen_state.BeersActivityState

/**
 * This [BaseMvvmViewModel] handles the 'splash' feature view-model. Therefore, it is in charge of
 * the logic which makes the app loads all the necessary data for a proper performance. It uses no
 * bridge whatsoever.
 *
 * All results update an observable variable, [_screenState], with [BeersActivityState] values.
 */
class BeersActivityViewModel(bridge: BaseDomainLayerBridge.None) :
    BaseMvvmViewModel<BaseDomainLayerBridge.None, BeersActivityState>(bridge = bridge) {


}
