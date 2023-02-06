package com.example.bankuishdemo.ui.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bankuishdemo.data.GitHubService
import com.example.bankuishdemo.data.response.GitHubResponseItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubRepositoryDataSource @Inject constructor(private val service: GitHubService) {

    fun getGitHubRepositories(): Flow<PagingData<GitHubResponseItems>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GitHubRepositoryPagingSource(service)
            }
        ).flow
    }
}