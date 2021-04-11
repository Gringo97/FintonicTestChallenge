package com.fintonic.presentation_layer.domain

import com.fintonic.domain_layer.domain.FailureBo


private const val DEFAULT_STRING_VALUE = "none"


/**
 * Extension function which maps a failure Business Object to a failure Visual Object
 *
 * @return the [FailureVo] type equivalent datum
 */
fun FailureBo.boToVoFailure(): FailureVo =
    when (this) {
        is FailureBo.InputParamsError -> FailureVo.Error(msg = msg)
        is FailureBo.RequestError -> FailureVo.Error(msg = msg)
        is FailureBo.ServerError -> FailureVo.Error(msg = msg)
        is FailureBo.NoData -> FailureVo.NoData
        is FailureBo.NoConnection -> FailureVo.NoConnection
        is FailureBo.Unknown -> FailureVo.Unknown
        is FailureBo.Error -> FailureVo.Error(msg = msg)
    }
