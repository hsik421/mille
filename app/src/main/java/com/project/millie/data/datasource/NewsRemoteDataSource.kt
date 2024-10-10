package com.project.millie.data.datasource

import com.project.millie.data.ApiService
import com.project.millie.data.model.NewsResponse
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loadItems(): NewsResponse = apiService.loadItems()
}