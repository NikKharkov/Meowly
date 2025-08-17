package org.catstagram.trackpet.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.catstagram.trackpet.domain.news.News
import org.catstagram.trackpet.util.BASE_URL

class NewsApi(private val httpClient: HttpClient) {
    suspend fun getAllNews(language: String): Result<List<News>> {
        return try {
            val news = httpClient.get("$BASE_URL/news") {
                parameter("lang",language)
            }.body<List<News>>()
            Result.success(news)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}