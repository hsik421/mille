package com.project.millie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val _newItems = MutableLiveData<List<News>>(listOf())
    val newsItems get() = _newItems

    init {
        viewModelScope.launch {
            loadItem()
        }
    }

    private suspend fun loadItem() {
        Log.i("hsik","loadItem")
        when (val result = newsRepository.loadItem()) {
            is ApiResult.Success -> {
                _newItems.value = result.data.articles
            }
            else -> {

            }
        }
    }

//    fun moreLoadItem() {
//        viewModelScope.launch {
//            val nextPage = newsItems.value?.size?:0
//            loadItem(nextPage)
//        }
//    }
}