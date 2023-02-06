package com.example.bankuishdemo.usecases

import com.example.bankuishdemo.data.GitHubService
import com.example.bankuishdemo.data.response.GitHubResponse
import javax.inject.Inject

class GetGitHubRepositoriesUseCase @Inject constructor(private val service: GitHubService) {

    suspend operator fun invoke(searchQuery: String): GitHubResponse? {
        return service.getGitHubRepositories(searchQuery)
    }
}