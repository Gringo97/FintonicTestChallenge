package com.fintonic.domain_layer.feature

import arrow.core.Either
import com.fintonic.domain_layer.DomainLayerContract
import com.fintonic.domain_layer.base.BaseDomainLayerBridge
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.domain.FailureBo
import kotlinx.coroutines.CoroutineScope

const val BEERS_BRIDGE_TAG = "beersDomainLayerBridge"

interface BeersDomainLayerBridge<out S> : BaseDomainLayerBridge {


    /**
     * A function blueprint to fetch the list of beers.
     *
     * @param [scope] The [CoroutineScope] associated, which can be used to handle an enclosing Kotlin Coroutine
     * @param [onResult] A callback to send back any data of interest
     */
    fun fetchBeersList(
        scope: CoroutineScope,
        onResult: (Either<FailureBo, S>) -> Unit = {}
    )
}


internal class BeersDomainLayerBridgeImpl(
    private val fetchBeersListUc: DomainLayerContract.PresentationLayer.UseCase<Nothing, List<Beer>>
) : BeersDomainLayerBridge<List<Beer>> {
    override fun fetchBeersList(
        scope: CoroutineScope,
        onResult: (Either<FailureBo, List<Beer>>) -> Unit
    ) {
        fetchBeersListUc.invoke(
            scope = scope,
            onResult = onResult
        )
    }
}
