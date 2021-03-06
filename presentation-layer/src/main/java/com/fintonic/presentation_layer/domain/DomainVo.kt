package com.fintonic.presentation_layer.domain

import com.fintonic.domain_layer.domain.ErrorMessage


data class BeerVo(
    val id: Int,
    val imgUrl : String,
    val name : String
)


/**
 * A class which models any failure coming from the 'domain-layer' module
 */
sealed class FailureVo(var msg: String?) {

    companion object {
        private const val DEFAULT_STRING_RESOURCE = "none"
    }

    /**
     * Allows to get the message associated to an error
     */
    fun getErrorMessage(): String = msg ?: DEFAULT_STRING_RESOURCE

    object NoConnection : FailureVo(msg = ErrorMessage.ERROR_NO_CONNECTION)
    object NoData : FailureVo(msg = ErrorMessage.ERROR_NO_DATA)
    object Unknown : FailureVo(msg = ErrorMessage.ERROR_UNKNOWN)
    class Error(msg: String) : FailureVo(msg = msg)

}
