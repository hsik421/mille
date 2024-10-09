package com.project.millie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun loadItems() : List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<News>)

    @Query("SELECT * FROM news WHERE publishedAt IN (:items) and isVisited = 1")
    suspend fun getVisitedItems(items : List<String>) : List<News>
}