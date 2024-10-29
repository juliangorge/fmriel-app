package org.juliangorge.fmriel.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.juliangorge.fmriel.util.NetworkError
import org.juliangorge.fmriel.util.Result

class PostsClient (
    private val httpClient: HttpClient
) {
    suspend fun mainHighlights(): Result<List<Any>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://fmriel-api.vercel.app/api/mainHighlights"
            ) {
                bearerAuth("eyJhbGciOiJIUzI1NiIsImtpZCI6ImdVY3RnUHh4cFpENW1NSkQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3JoeHpzdWNzdG5kdHRwcHVzbWhoLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJjOTAwMWM5MC05MTcyLTQwNmEtYmZmZi1hNDMxYjgyNzg5YmIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzMwMTQ0MDI3LCJpYXQiOjE3MzAxNDA0MjcsImVtYWlsIjoianVsaWFuZ29yZ2VAaG90bWFpbC5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7fSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTczMDE0MDQyN31dLCJzZXNzaW9uX2lkIjoiYWJmZjRmOTktZjAxNy00MjcwLWFkNDgtMjRkZjQ4NmQ1NTdmIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.D1gwd06eqfWx7u04eejLyl_LUF6hho8GdUReISv359E")
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                Result.Success(response.body())
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}