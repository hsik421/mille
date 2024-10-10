package com.project.millie.ui.view

import android.content.Intent
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.project.millie.ui.BaseAct
import com.project.millie.utils.GridSpacingItemDecoration
import com.project.millie.ui.adapter.NewsAdapter
import com.project.millie.ui.viewmodel.NewsViewModel
import com.project.millie.R
import com.project.millie.data.ViewConstants
import com.project.millie.databinding.ActMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainAct : BaseAct<ActMainBinding>(R.layout.act_main) {

    private val newsViewModel: NewsViewModel by viewModels()

    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(newsViewModel) }

    override fun initView() {
        dataBinding.viewModel = newsViewModel
        with(dataBinding.newsList) {
            adapter = newsAdapter
            addItemDecoration(GridSpacingItemDecoration(20,true))
        }
    }

    override fun initObserver() {
        newsViewModel.newsItems.observe(this) {
            newsAdapter.submitList(it)
        }

        newsViewModel.startWebViewEvent.observe(this) {
            startActivity(Intent(this, WebViewAct::class.java).apply {
                putExtra(ViewConstants.VIEW_URL_PARAM,it)
            })
        }
        newsViewModel.showAlertEvent.observe(this) {
            Snackbar.make(dataBinding.coordinatorLayout,it,Snackbar.LENGTH_SHORT).show()
        }
    }
}