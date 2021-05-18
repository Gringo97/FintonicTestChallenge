package com.fintonic.data_layer.remote

import arrow.core.Either
import com.fintonic.data_layer.domain.dtoToBo
import com.fintonic.data_layer.service.PunkApi
import com.fintonic.domain_layer.domain.Beer
import com.fintonic.domain_layer.domain.FailureBo
import com.fintonic.util.safeCall
import retrofit2.Retrofit

interface BeersRemoteDataSource {

    companion object {
        const val BEERS_REMOTE_DATA_SOURCE_TAG = "beersRemoteDataSource"
        const val RETROFIT_BEERS_TAG = "RETROFIT_BEERS_TAG"
        const val BASE_PUNK_API_URL = "https://api.punkapi.com"
    }

    suspend fun fetchBeersList(): Either<FailureBo, List<Beer>>
}

/**
 * This class complies with [BeersRemoteDataSource] so that it is in charge of providing any required
 * information regarding beers
 */
class BeersRemoteDataSourceImpl(private val retrofitBuilder: Retrofit) : BeersRemoteDataSource {
    override suspend fun fetchBeersList() =
        retrofitBuilder.create(PunkApi::class.java).getBeersAsync().safeCall(
            transform = { listBeerDto ->
                listBeerDto.map { beerDto ->
                    beerDto.dtoToBo()
                }
            }
        )
}