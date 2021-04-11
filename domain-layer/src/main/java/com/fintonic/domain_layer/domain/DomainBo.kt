package com.fintonic.domain_layer.domain


private const val DEFAULT_STRING_RESOURCE = "none"


data class Beer(
    val abv: Double,
    val attenuationLevel: Double,
    val boilVolume: String,
    val brewersTips: String,
    val contributedBy: String,
    val description: String,
    val ebc: Int?,
    val firstBrewed: String,
    val foodPairing: List<String>,
    val ibu: Double?,
    val id: Int,
    val imageUrl: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double?,
    val srm: Double?,
    val tagLine: String,
    val targetFg: Double,
    val targetOg: Double,
    val volume: String
)

data class Ingredients(

    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)

data class Hop(
    val add: String,
    val amount: String,
    val attribute: String,
    val name: String
)

data class Malt(
    val amount: String,
    val name: String
)

data class Method(
    val fermentation: Fermentation,
    val mashTemp: List<MashTemp>,
    val twist: String?
)

data class Fermentation(
    val temp: String
)

data class MashTemp(
    val duration: Double?,
    val temp: String
)

/**
 * A class which models any failure coming from the 'domain-layer'
 */
sealed class FailureBo(var msg: String = DEFAULT_STRING_RESOURCE) {
    object NoConnection : FailureBo(msg = ErrorMessage.ERROR_NO_CONNECTION)
    class InputParamsError(msg: String) : FailureBo(msg = msg)
    class RequestError(msg: String) : FailureBo(msg = msg)
    class ServerError(msg: String) : FailureBo(msg = msg)
    object NoData : FailureBo(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureBo(msg = ErrorMessage.ERROR_UNKNOWN)
    class Error(msg: String) : FailureBo(msg = msg)
}

/**
 * This object gathers all error messages available throughout the app
 */
object ErrorMessage {
    const val ERROR_NO_CONNECTION = "No Connection"
    const val ERROR_NO_DATA = "No Data"
    const val ERROR_BAD_REQUEST = "Bad Request"
    const val ERROR_SERVER = "Server Error"
    const val ERROR_UNKNOWN = "Unknown Error"
}


