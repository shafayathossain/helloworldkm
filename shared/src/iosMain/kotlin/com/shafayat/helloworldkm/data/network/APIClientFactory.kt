package com.shafayat.helloworldkm.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual class APIClientFactory(actual val baseUrl: String) {

    actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient(Darwin) {
        config(this)
        engine{
            configureRequest{
                setAllowsCellularAccess(true)
            }
        }
    }
}