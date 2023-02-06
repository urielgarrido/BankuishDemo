package com.example.bankuishdemo.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepositoryModel(
    val repositoryName: String,
    val ownerName: String,
    val imageUrl: String,
    val description: String,
    val createdAt: String,
    val license: String? = null,
    val watchers: Int
) : Parcelable
