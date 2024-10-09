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
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            when(val result = newsRepository.loadItems()){
                is ApiResult.Success->{
                    _newItems.value = result.data
                }
                is ApiResult.Failure->{
                    _newItems.value = result.data
                }
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