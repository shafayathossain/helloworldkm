package com.shafayat.helloworldkm.data.network

import com.shafayat.helloworldkm.data.local.AppPreferences
import com.shafayat.helloworldkm.data.network.model.safeApiCall
import com.shafayat.helloworldkm.data.network.utils.NetworkResultState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmName

class ApiClient private constructor() {

    companion object {

        @set:JvmName("ApiClientPreferences")
        private lateinit var preferences: AppPreferences

        @set:JvmName("ApiClientClientFactory")
        private lateinit var clientFactory: APIClientFactory

        val instance: ApiClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiClient(preferences, clientFactory)
        }

        fun setup(
            preferences: AppPreferences,
            clientFactory: APIClientFactory
        ) {
            this.preferences = preferences
            this.clientFactory = clientFactory

            instance
        }
    }

    lateinit var httpClient: HttpClient

    private constructor(
        preferences: AppPreferences,
        clientFactory: APIClientFactory
    ) : this() {
        httpClient = clientFactory.httpClient() {
            defaultRequest {
                url(clientFactory.baseUrl)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

//            install(Auth) {
//                bearer {
//                    BearerTokens(preferences.accessToken, preferences.refreshToken)
//                    refreshTokens {
//                        // TODO("Make refresh token request here and save into preference")
//                        BearerTokens(preferences.accessToken, preferences.refreshToken)
//                    }
//                }
//            }

            HttpResponseValidator {
                validateResponse { response ->
                    when (response.status.value) {
                        in 300..399 -> throw RedirectResponseException(response, "")
                        in 400..499 -> throw ClientRequestException(response, "")
                        in 500..599 -> throw ServerResponseException(response, "")
                    }
                }
            }
            install(Logging) {
                logger = Logger.Companion.SIMPLE
                level = LogLevel.ALL
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }
    }
}

inline fun <reified T> ApiClient.get(
    endpoint: String,
    parameters: Map<String, Any> = mapOf()
): Flow<NetworkResultState<T>> {
    return safeApiCall {
        httpClient.get(endpoint) {
            url {
                parameters.forEach {
                    this.parameters.append(it.key, it.value.toString())
                }
            }
        }.body()
    }
}

inline fun <reified T> ApiClient.post(
    endpoint: String,
    requestBody: Any
): Flow<NetworkResultState<T>> {
    return safeApiCall {
        httpClient.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }
}

sealed interface ApiResponse<T> {
    data class Success<T>(val response: T) : ApiResponse<T>
    data class Error<T>(val exception: Exception) : ApiResponse<T>
}