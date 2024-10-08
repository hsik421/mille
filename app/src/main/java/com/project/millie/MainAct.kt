package com.project.millie

import android.util.Log
import com.project.millie.databinding.ActMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView

@AndroidEntryPoint
class MainAct : BaseAct<ActMainBinding>(R.layout.act_main) {

    private val newsViewModel : NewsViewModel by viewModels()

    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    override fun initView() {
        with(dataBinding.newsList) {
            adapter = newsAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(!canScrollVertically(1)) {
                        newsViewModel.moreLoadItem()
                    }
                }
            })
        }
    }

    override fun initObserver() {
        newsViewModel.newsItems.observe(this){
            Log.i("hsik","newsViewModel.newsItems = $it")
            newsAdapter.submitList(it)
        }
    }

}