package com.fintonic.presentation_layer.beer_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.feature.BeersDomainLayerBridge
import com.fintonic.presentation_layer.base.BaseMvvmView
import com.fintonic.presentation_layer.base.ScreenState
import com.fintonic.presentation_layer.beer_list.adapter.BeerActionView
import com.fintonic.presentation_layer.beer_list.adapter.BeersAdapter
import com.fintonic.presentation_layer.beer_list.screen_state.BeersListState
import com.fintonic.presentation_layer.beer_list.viewmodels.BeersListViewModel
import com.fintonic.presentation_layer.databinding.BeersListFragmentBinding
import com.fintonic.presentation_layer.domain.BeerVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class BeersListFragment : Fragment(),

    BaseMvvmView<BeersListViewModel, BeersDomainLayerBridge<List<Beer>>, BeersListState> {

    override val viewModel: BeersListViewModel by viewModel()

    private lateinit var binding: BeersListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = BeersListFragmentBinding.inflate(
        LayoutInflater.from(context),
        container,
        false
    ).apply {
        binding = this
        initView()
        initModel()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }


    private fun initView() {
        with(binding.beersRv) {
            adapter = BeersAdapter(beerList = mutableListOf()) { action ->
                when (action) {
                    is BeerActionView.BeerItemClick -> {
                        Toast.makeText(
                            context,
                            "Has elegido la cerveza:${action.item.name}",
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.loadBeerDetail(action.item)
                    }
                }
            }
        }
    }

    override fun initModel() {
        lifecycleScope.launch {
            viewModel.screenState.collect { screenState ->
                when (screenState) {
                    is ScreenState.Idle -> hideLoading()
                    is ScreenState.Loading -> showLoading()
                    is ScreenState.Render<BeersListState> -> processRenderState(screenState.renderState)
                }
            }
        }
    }

    override fun processRenderState(renderState: BeersListState) {

        when (renderState) {
            is BeersListState.LoadBeers -> loadBeersData(renderState.beerList)
        }
    }

    private fun loadBeersData(data: List<BeerVo>) {
        with(binding) {
            (beersRv.adapter as? BeersAdapter)?.updateData(data)
        }
    }

    private fun showLoading() {
        //TODO ADD LOADER
    }

    private fun hideLoading() {
        //TODO HIDE LOADER
    }


}