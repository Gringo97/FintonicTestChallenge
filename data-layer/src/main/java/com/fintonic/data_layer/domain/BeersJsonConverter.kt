package com.fintonic.data_layer.domain

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class Wrapped

class VideosJsonConverter {
    @Wrapped
    @FromJson
    fun fromJson(json: BeersDtoWrapper): List<BeerDto> {
        return json.value
    }
}