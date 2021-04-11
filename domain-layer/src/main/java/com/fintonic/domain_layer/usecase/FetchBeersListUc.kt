package com.fintonic.domain_layer.usecase

import arrow.core.Either
import com.fintonic.domain_layer.DomainLayerContract
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.domain.FailureBo


const val FETCH_BEERS_LIST_UC_TAG = "fetchBeersListUcTag"

class FetchBeersListUc(private val repository: DomainLayerContract.DataLayer.BeersListRepository<List<Beer>>) :
    DomainLayerContract.PresentationLayer.UseCase<Nothing, List<Beer>> {
    override suspend fun run(params: Nothing?): Either<FailureBo, List<Beer>> =
        repository.fetchBeersList()

}