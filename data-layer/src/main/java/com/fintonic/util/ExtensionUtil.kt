package com.fintonic.util

import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.fintonic.data_layer.domain.FailureDto
import com.fintonic.data_layer.domain.dtoToBoFailure
import com.fintonic.domain_layer.domain.ErrorMessage
import com.fintonic.domain_layer.domain.FailureBo
import retrofit2.Response


/**
 * This extension function provides a proceeding to handle with 'Retrofit' [Response] objects, so that
 * a parametrized 'Either' type is returned.
 *
 */
fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorHandler: (Response<T>).() -> Either<FailureBo, Nothing> = { handleDataSourceError() }
): Either<FailureBo, R> =
    try {
        run {
            if (isSuccessful) {
                val body = body()
                if (body != null) {
                    transform(body).right()
                } else {
                    errorHandler()
                }
            } else {
                errorHandler()
            }
        }
    } catch (exception: Exception) {
        Log.e("t", "Error: ${exception.message}")
        errorHandler()
    }

/**
 * This extension function provides a mechanism to handle HTTP errors coming from a Retrofit
 * [Response]. It returns an [Either] which models the [FailureBo] to be transmitted beyond the
 * domain layer.
 *
 */
fun <T> Response<T>?.handleDataSourceError(): Either<FailureBo, Nothing> =
    when (this?.code()) {
        in 400..499 -> FailureDto.RequestError(code = 400, msg = ErrorMessage.ERROR_BAD_REQUEST)
        in 500..599 -> FailureDto.RequestError(code = 500, msg = ErrorMessage.ERROR_SERVER)
        else -> FailureDto.Unknown
    }.dtoToBoFailure().left()








