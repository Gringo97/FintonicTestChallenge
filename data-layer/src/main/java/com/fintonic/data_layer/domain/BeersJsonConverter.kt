package com.fintonic.data_layer.domain

import android.provider.MediaStore.Video
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson


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