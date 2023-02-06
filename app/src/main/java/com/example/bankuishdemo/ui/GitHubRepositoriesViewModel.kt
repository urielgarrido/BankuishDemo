package com.example.bankuishdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel
import com.example.bankuishdemo.usecases.GetGitHubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(
    private val getGitHubRepositoriesUseCase: GetGitHubRepositoriesUseCase
) : ViewModel() {

    fun getGitHubRepositories(): Flow<PagingData<GitHubRepositoryModel>> {
        return getGitHubRepositoriesUseCase().map { pagingData ->
            pagingData.map {
                GitHubRepositoryModel(
                    it.name,
                    it.owner.ownerName,
                    it.owner.avatarUrl,
                    it.description,
                    it.createdAt,
                    it.license?.name,
                    it.watchers
                )
            }
        }.cachedIn(viewModelScope)
    }
}