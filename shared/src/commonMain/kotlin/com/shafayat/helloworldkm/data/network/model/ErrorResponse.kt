package com.shafayat.helloworldkm.data.network.model

import kotlinx.serialization.Serializable

@Serializable
open class ErrorResponse(
    open val error: String
): Exception(error)

class LoginErrorResponse(error: String): ErrorResponse(error)
class NoInternetConnectionErrorResponse(error: String): ErrorResponse(error)