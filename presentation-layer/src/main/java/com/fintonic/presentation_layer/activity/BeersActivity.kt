package com.fintonic.presentation_layer.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fintonic.domain_layer.base.BaseDomainLayerBridge
import com.fintonic.presentation_layer.R
import com.fintonic.presentation_layer.base.BaseMvvmView
import com.fintonic.presentation_layer.screen_state.BeersActivityState
import com.fintonic.presentation_layer.viewmodels.BeersActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BeersActivity :
    AppCompatActivity(),
    BaseMvvmView<BeersActivityViewModel, BaseDomainLayerBridge.None, BeersActivityState> {

    override val viewModel: BeersActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fintonic)
    }


    override fun processRenderState(renderState: BeersActivityState?) {}
}