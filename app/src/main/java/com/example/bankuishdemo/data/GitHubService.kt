package com.example.bankuishdemo.data

import com.example.bankuishdemo.data.response.GitHubResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubService @Inject constructor(private val gitHubApi: GitHubApi) {

    suspend fun getGitHubRepositories(searchQuery: String, page: Int): GitHubResponse? {
        return withContext(Dispatchers.IO) {
            val response = gitHubApi.getGitHubRepositories(searchQuery, 30, page)
            if (response.isSuccessful) response.body()
            else null
        }
    }
}