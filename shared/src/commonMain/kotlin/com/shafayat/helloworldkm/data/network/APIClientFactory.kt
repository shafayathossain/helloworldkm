package com.shafayat.helloworldkm.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect class APIClientFactory {

    val baseUrl: String

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
}
