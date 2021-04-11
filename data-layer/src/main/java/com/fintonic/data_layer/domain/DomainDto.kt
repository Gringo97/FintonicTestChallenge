package com.fintonic.data_layer.domain

import com.fintonic.domain_layer.domain.ErrorMessage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.ResponseBody

@JsonClass(generateAdapter = true)
data class BeersDtoWrapper(val value : List<BeerDto>)

@JsonClass(generateAdapter = true)
data class BeerDto(
    @Json(name = "abv")
    val abv: Double,
    @Json(name = "attenuation_level")
    val attenuationLevel: Double,
    @Json(name = "boil_volume")
    val boilVolume: UnitValueDto,
    @Json(name = "brewers_tips")
    val brewersTips: String,
    @Json(name = "contributed_by")
    val contributedBy: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "ebc")
    val ebc: Int?,
    @Json(name = "first_brewed")
    val firstBrewed: String,
    @Json(name = "food_pairing")
    val foodPairing: List<String>,
    @Json(name = "ibu")
    val ibu: Double?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "ingredients")
    val ingredients: IngredientsDto,
    @Json(name = "method")
    val method: MethodDto,
    @Json(name = "name")
    val name: String,
    @Json(name = "ph")
    val ph: Double?,
    @Json(name = "srm")
    val srm: Double?,
    @Json(name = "tagline")
    val tagLine: String,
    @Json(name = "target_fg")
    val targetFg: Double,
    @Json(name = "target_og")
    val targetOg: Double,
    @Json(name = "volume")
    val volumeDto: UnitValueDto
)

@JsonClass(generateAdapter = true)
data class FoodPairingWrapper(
   val list : List<String>
)

@JsonClass(generateAdapter = true)
data class IngredientsDto(
    @Json(name = "hops")
    val hops: List<HopDto>,
    @Json(name = "malt")
    val malt: List<MaltDto>,
    @Json(name = "yeast")
    val yeast: String
)
@JsonClass(generateAdapter = true)
data class HopDto(
    @Json(name = "add")
    val add: String,
    @Json(name = "amount")
    val amount: UnitValueDto,
    @Json(name = "attribute")
    val attribute: String,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class MaltDto(
    @Json(name = "amount")
    val amount: UnitValueDto,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class MethodDto(
    @Json(name = "fermentation")
    val fermentation: FermentationDto,
    @Json(name = "mash_temp")
    val mashTemp: List<MashTempDto>,
    @Json(name = "twist")
    val twist: String?
)

@JsonClass(generateAdapter = true)
data class FermentationDto(
    @Json(name = "temp")
    val temp: UnitValueDto
)

@JsonClass(generateAdapter = true)
data class MashTempDto(
    @Json(name = "duration")
    val duration: Double?,
    @Json(name = "temp")
    val temp: UnitValueDto
)

@JsonClass(generateAdapter = true)
data class UnitValueDto(
    @Json(name = "unit")
    val unit: String,
    @Json(name = "value")
    val value: Double
)

/**
 * A class which models any failure coming from the 'data-layer'
 */
sealed class FailureDto(val msg: String?) {

    companion object {
        private const val DEFAULT_ERROR_CODE = -1
    }

    object NoConnection : FailureDto(msg = ErrorMessage.ERROR_NO_CONNECTION)
    class RequestError(
        val code: Int = DEFAULT_ERROR_CODE,
        msg: String?,
        val errorBody: ResponseBody? = null
    ) : FailureDto(msg = msg)
    object NoData : FailureDto(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureDto(msg = ErrorMessage.ERROR_UNKNOWN)
    class Error(msg: String?) : FailureDto(msg = msg)
}


