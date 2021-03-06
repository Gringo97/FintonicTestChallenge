package com.fintonic.data_layer.service

import com.fintonic.data_layer.domain.BeerDto
import retrofit2.Response
import retrofit2.http.GET


interface PunkApi {

    @GET("v2/beers")
    suspend fun getBeersAsync(): Response<List<BeerDto>>
}