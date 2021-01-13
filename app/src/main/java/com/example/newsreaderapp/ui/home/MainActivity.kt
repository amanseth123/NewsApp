package com.example.newsreaderapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsreaderapp.R
import com.example.newsreaderapp.databinding.ActivityMainBinding
import com.example.newsreaderapp.models.ArticlesItem
import com.example.newsreaderapp.models.TopHeadlinesResponse
import com.example.newsreaderapp.network.ApiClient
import com.example.newsreaderapp.ui.readnews.ReadNewsWebView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity(),Callback<TopHeadlinesResponse> {

    var news_list_adapter: NewsListAdapter? = null
    var news_list = ArrayList<ArticlesItem>()
    private var news_recycler_view: RecyclerView? = null
    private var on_news_click: NewsListClickListener? = null

    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        on_news_click = NewsListClickListener { news_url ->
            var intent = Intent(MainActivity@this, ReadNewsWebView::class.java)
            intent.putExtra("url",news_url)
            startActivity(intent)
        }

        news_recycler_view = mBinding.recyclerView
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        news_recycler_view!!.layoutManager = mLayoutManager
        news_list_adapter = NewsListAdapter(this, news_list, on_news_click!!)
        news_recycler_view!!.adapter = news_list_adapter

        mBinding.progressBar.visibility = View.VISIBLE

        ApiClient.getInstance().getApi()
            .topHeadlines("in")
            .enqueue(this)
    }

    override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
        mBinding.progressBar.visibility = View.GONE
        Toast.makeText(this,"Faild to get news",Toast.LENGTH_SHORT).show()

    }

    override fun onResponse(
        call: Call<TopHeadlinesResponse>,
        response: Response<TopHeadlinesResponse>
    ) {
        System.out.println(response.body())
        if(response?.body()?.totalResults!! > 0) {
//            news_list_adapter?.newsList?.clear()
            var all_news_items_from_api = response?.body()?.articles
            news_list_adapter?.newsList = all_news_items_from_api as List<ArticlesItem>
//            news_list_adapter?.newsList?.addAll()
            news_list_adapter?.notifyDataSetChanged()
        }
        mBinding.progressBar.visibility = View.GONE
    }
}