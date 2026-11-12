package com.hasancbngl.bookapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform