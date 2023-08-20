package com.shafayat.helloworldkm.data.network.model


import com.shafayat.helloworldkm.data.network.utils.NetworkResultState
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T : Any?> safeApiCall(apiCall: suspend () -> T): Flow<NetworkResultState<T>> {
    return flow {
        try {
            emit(NetworkResultState.Loading)

            emit(NetworkResultState.Success(apiCall.invoke()))
        } catch (e: RedirectResponseException) {
            val error = parseNetworkError(e.response.body())
            emit(NetworkResultState.Failure(exception = error))
        } catch (e: ClientRequestException) {
            val error = parseNetworkError(e.response.body())
            emit(NetworkResultState.Failure(exception = error))
        } catch (e: ServerResponseException) {
            val error = parseNetworkError(e.response.body())
            emit(NetworkResultState.Failure(exception = error))
        } catch (e: UnresolvedAddressException) {
            val error = parseNetworkError(exception = e)
            emit(NetworkResultState.Failure(exception = error))
        } catch (e: Exception) {
            val error = parseNetworkError(exception = e)
            emit(NetworkResultState.Failure(exception = error))
        }
    }
}

/**Generate [Exception] from network or system error when making network calls
 *
 * @throws [Exception]
 * */
internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): ErrorResponse {
    var errorBody: ErrorResponse? = errorResponse?.body<ErrorResponse>()
    if (errorBody?.error?.contains("incorrect") == true) {
        errorBody = LoginErrorResponse(errorBody.error)
    }
    if (exception != null) {
        val className = exception::class.simpleName
        if (
            className?.contains("UnknownHostException", true) == true ||
            className?.contains("DarwinHttpRequestException", true) == true
        ) {
            errorBody = NoInternetConnectionErrorResponse(exception.message ?: "")
        }
    }

    return errorBody ?: ErrorResponse(
        error = exception?.message ?: "Error"
    )
}