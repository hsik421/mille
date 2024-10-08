package com.project.millie

class NewsRepositoryImpl : NewsRepository{
    override suspend fun loadItem(id: Int) {

    }
}

interface NewsRepository{
    suspend fun loadItem(id : Int)

}