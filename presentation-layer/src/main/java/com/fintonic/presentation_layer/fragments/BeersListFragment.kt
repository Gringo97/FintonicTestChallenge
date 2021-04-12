package com.fintonic.presentation_layer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.feature.BeersDomainLayerBridge
import com.fintonic.presentation_layer.base.BaseMvvmView
import com.fintonic.presentation_layer.databinding.BeersListFragmentBinding
import com.fintonic.presentation_layer.screen_state.BeersListState
import com.fintonic.presentation_layer.viewmodels.BeersListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

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
        binding.apply {
            //  viewModel = mViewModel
            //  lifecycleOwner = viewLifecycleOwner
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }


    override fun processRenderState(renderState: BeersListState?) {
        TODO("Not yet implemented")
    }

}