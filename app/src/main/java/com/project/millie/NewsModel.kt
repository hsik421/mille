package com.project.millie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Int,
    val urlToImage: String,
    val url: String,
    val title: String,
    val publishedAt: String,
) : Parcelable

@Parcelize
data class NewsResponse(
    val status : String,
    val totalResults : Int,
    val articles : List<News>,
) : Parcelable
