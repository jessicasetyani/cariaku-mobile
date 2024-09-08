package com.styletheory.cariaku

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform