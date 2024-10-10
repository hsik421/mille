package com.project.millie.ui.view

import com.project.millie.ui.BaseAct
import com.project.millie.R
import com.project.millie.data.ViewConstants
import com.project.millie.databinding.ActWebviewBinding

class WebViewAct : BaseAct<ActWebviewBinding>(R.layout.act_webview) {
    override fun initView() {
        intent.getStringExtra(ViewConstants.VIEW_URL_PARAM)?.let {
            dataBinding.webview.loadUrl(it)
        }
    }
    override fun initObserver() = Unit
}