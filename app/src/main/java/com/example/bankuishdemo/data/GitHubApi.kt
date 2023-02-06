package com.example.bankuishdemo.data

import com.example.bankuishdemo.data.response.GitHubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    suspend fun getGitHubRepositories(
        @Query("q") searchQuery: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<GitHubResponse>
}