package com.example.bankuishdemo.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bankuishdemo.data.GitHubService
import com.example.bankuishdemo.data.response.GitHubResponseItems
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val START_PAGE = 1

class GitHubRepositoryPagingSource @Inject constructor(
    private val service: GitHubService
) : PagingSource<Int, GitHubResponseItems>() {

    override fun getRefreshKey(state: PagingState<Int, GitHubResponseItems>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubResponseItems> {
        val pageIndex = params.key ?: START_PAGE
        return try {
            val response = service.getGitHubRepositories(
                searchQuery = "kotlin",
                page = pageIndex
            )
            val gitHubRepositories = response?.items
            val nextKey =
                if (gitHubRepositories.isNullOrEmpty()) {
                    null
                } else {
                    pageIndex + 1
                }
            LoadResult.Page(
                data = gitHubRepositories.orEmpty(),
                prevKey = if (pageIndex == START_PAGE) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}