package org.juliangorge.fmriel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform