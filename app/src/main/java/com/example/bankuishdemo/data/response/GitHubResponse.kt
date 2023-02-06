package com.example.bankuishdemo.data.response

import com.google.gson.annotations.SerializedName

data class GitHubResponse(
    @SerializedName("items") val items: List<GitHubResponseItems>
)

data class GitHubResponseItems(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("license") val license: License?,
)

data class Owner(
    @SerializedName("login") val ownerName: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)

data class License(
    @SerializedName("name") val name: String
)
