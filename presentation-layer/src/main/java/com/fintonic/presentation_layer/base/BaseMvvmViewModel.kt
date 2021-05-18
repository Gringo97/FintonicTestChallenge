package com.fintonic.presentation_layer.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fintonic.domain_layer.base.BaseDomainLayerBridge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

/**
 * This parametrized abstract class is intended to be extended by any app presentation-layer view-model which aims to be
 * integrated within the MVVM architecture pattern.
 *
 * @param S represents the state of the view, and must extend from [BaseState]
 * @property screenState the [LiveData] which will be updated to notify the view
 * @property viewModelJob represents the job to be launched for the coroutine
 * @property coroutineContext a context to host the coroutine
 *
 */
@ExperimentalCoroutinesApi
abstract class BaseMvvmViewModel<T : BaseDomainLayerBridge, S : BaseState>(protected val bridge: T) :
    ViewModel(){

    protected val _screenState: MutableStateFlow<ScreenState<S>> by lazy { MutableStateFlow(ScreenState.Idle) }
    val screenState: StateFlow<ScreenState<S>>
        get() {
            return _screenState
        }


}