package org.juliangorge.fmriel

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class Greeting {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/welcome.html")
        return response.bodyAsText()
    }

    fun greet(): String {
        return "Hello, world!"
    }
}