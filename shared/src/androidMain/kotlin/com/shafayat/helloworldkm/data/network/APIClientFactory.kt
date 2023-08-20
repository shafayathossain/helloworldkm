package com.shafayat.helloworldkm.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

actual class APIClientFactory(actual val baseUrl: String) {

    actual fun httpClient(config: HttpClientConfig<*>.()-> Unit) = HttpClient(OkHttp) {
        config(this)
        engine{
            config{
                retryOnConnectionFailure(true)
                connectTimeout(5, TimeUnit.SECONDS)
            }
        }
    }
}