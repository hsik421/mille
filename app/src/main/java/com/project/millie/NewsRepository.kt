package com.project.millie

import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) {
    suspend fun loadItem(): ApiResult<NewsResponse> {
        return callApi {
            remoteDataSource.loadItem()
        }
    }
}
