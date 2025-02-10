package com.news.noticiasapp.api

import com.news.noticiasapp.models.News
import retrofit2.http.*

interface ApiService {
    @GET("news")
    suspend fun getAllNews(): List<News>

    @GET("news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): News

    @POST("news")
    suspend fun createNews(@Body news: News): Map<String, Int>

    @PUT("news/{id}")
    suspend fun updateNews(@Path("id") id: Int, @Body news: News): Map<String, String>

    @DELETE("news/{id}")
    suspend fun deleteNews(@Path("id") id: Int): Map<String, String>
}
