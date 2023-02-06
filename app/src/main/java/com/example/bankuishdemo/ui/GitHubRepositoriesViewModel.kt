package com.example.bankuishdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel
import com.example.bankuishdemo.usecases.GetGitHubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(
    private val getGitHubRepositoriesUseCase: GetGitHubRepositoriesUseCase
) : ViewModel() {

    private val _gitHubRepositories = MutableLiveData<List<GitHubRepositoryModel>>()
    val gitHubRepositories: LiveData<List<GitHubRepositoryModel>> = _gitHubRepositories

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun getGitHubRepositories() {
        viewModelScope.launch {
            val repositories = getGitHubRepositoriesUseCase("kotlin")
            if (repositories != null) {
                val items: List<GitHubRepositoryModel> = repositories.items.map {
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
                _gitHubRepositories.postValue(items)
            } else {
                _error.postValue(true)
            }
        }
    }
}