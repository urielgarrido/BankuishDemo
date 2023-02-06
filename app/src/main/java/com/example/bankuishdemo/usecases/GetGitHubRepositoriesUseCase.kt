package com.example.bankuishdemo.usecases

import androidx.paging.PagingData
import com.example.bankuishdemo.data.response.GitHubResponseItems
import com.example.bankuishdemo.ui.paging.GitHubRepositoryDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGitHubRepositoriesUseCase @Inject constructor(private val dataSource: GitHubRepositoryDataSource) {

    operator fun invoke(): Flow<PagingData<GitHubResponseItems>> {
        return dataSource.getGitHubRepositories()
    }
}