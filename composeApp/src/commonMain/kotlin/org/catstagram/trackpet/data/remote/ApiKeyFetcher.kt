package org.catstagram.trackpet.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.catstagram.trackpet.util.BASE_URL

class ApiKeyFetcher(private val httpClient: HttpClient) {
    suspend fun getApiKey(): Result<String> {
        return try {
            val apiKey = httpClient.get("$BASE_URL/api").body<String>()
            Result.success(apiKey)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}