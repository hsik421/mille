package com.project.millie

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val publishedAt: String,
    val urlToImage: String?,
    val url: String,
    val title: String,
    var isVisited : Int = 0
) : Parcelable{
    @IgnoredOnParcel
    val fileName  get() =  title.replace("\\W".toRegex(),"")
}

@Parcelize
data class NewsResponse(
    val status : String,
    val totalResults : Int,
    val articles : List<News>,
) : Parcelable
