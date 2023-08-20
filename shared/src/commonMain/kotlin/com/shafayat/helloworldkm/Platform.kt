package com.shafayat.helloworldkm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform