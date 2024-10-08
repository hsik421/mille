package com.project.millie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(): ViewModel() {

    private val _newItems = MutableLiveData<List<News>>(listOf())
    val newsItems get() = _newItems

    init {
        viewModelScope.launch {
            loadItem(0)
        }
    }

    private suspend fun loadItem(page: Int) {
        Log.i("hsik","loadItem")
        when (val result = tempNews(page)) {
            is ApiResult.Success -> {
                _newItems.value = (_newItems.value?: emptyList()) + result.data
            }
            else -> {

            }
        }
    }

    fun moreLoadItem() {
        viewModelScope.launch {
            val nextPage = newsItems.value?.size?:0
            loadItem(nextPage)
        }
    }
}