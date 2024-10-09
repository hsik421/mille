package com.project.millie

import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) {
    suspend fun loadItems(): ApiResult<List<News>> {
        return try {
            if (remoteDataSource.loadItems().status == "ok") {
                remoteDataSource.loadItems().articles.let {
                    insertItems(it)
                    getVisitedItems(it)
                    ApiResult.Success(it)
                }
            } else {
                ApiResult.Failure(localDataSource.loadItems(), Exception("Response status not ok"))
            }
        } catch (e: Exception) {
            ApiResult.Failure(localDataSource.loadItems(), e)
        }
    }

    suspend fun insertItems(items: List<News>) {
        localDataSource.insertItems(items)
    }

    private suspend fun getVisitedItems(items: List<News>) {
        localDataSource.getVisitedItems(items.map { it.publishedAt }).forEach { local ->
            items.find { local.publishedAt == it.publishedAt }?.isVisited = 1
        }
    }
}
