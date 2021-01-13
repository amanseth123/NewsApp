package com.example.newsreaderapp.ui.readnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newsreaderapp.R
import com.example.newsreaderapp.databinding.ActivityMainBinding
import com.example.newsreaderapp.databinding.ActivityReadNewsWebViewBinding

class ReadNewsWebView : AppCompatActivity() {
    private lateinit var mBinding: ActivityReadNewsWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_read_news_web_view)

        var url = intent.getStringExtra("url")

        mBinding.webview.loadUrl(url)
    }
}