package com.project.millie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int,
    val urlToImage: String,
    val title: String,
    val publishedAt: String,
) : Parcelable

fun tempNews(id: Int) = ApiResult.Success((id until id + 10).map {
    News(
        id = it,
        urlToImage = "",
        title = "title : $it",
        publishedAt = "time : $it"
    )
}.toList())
