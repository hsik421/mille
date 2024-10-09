package com.project.millie

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loadItems(): NewsResponse = apiService.loadItems()
}