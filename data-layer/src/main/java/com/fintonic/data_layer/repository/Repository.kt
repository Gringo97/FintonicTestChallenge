package com.fintonic.data_layer.repository

import com.fintonic.data_layer.remote.BeersRemoteDataSource
import com.fintonic.domain_layer.DomainLayerContract
import com.fintonic.domain_layer.domain.Beer

object Repository : DomainLayerContract.DataLayer.BeersListRepository<List<Beer>> {

    lateinit var beersDataSource: BeersRemoteDataSource

    override suspend fun fetchBeersList() = beersDataSource.fetchBeersList()
}
