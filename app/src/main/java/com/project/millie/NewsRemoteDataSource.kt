package com.project.millie

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loadItem(): NewsResponse = apiService.loadItem()
}