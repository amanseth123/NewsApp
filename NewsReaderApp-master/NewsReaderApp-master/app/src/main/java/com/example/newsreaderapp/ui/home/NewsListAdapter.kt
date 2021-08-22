package com.example.newsreaderapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsreaderapp.R
import com.example.newsreaderapp.models.ArticlesItem

public class NewsListAdapter(var context: Context, var newsList:List<ArticlesItem>,var onClickInterface:NewsListClickListener):
    RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return NewsListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val news_item = newsList[position]
        holder.title_text_view.text = news_item.title

        Glide.with(context).load(news_item.urlToImage).into(holder.image_view)

        holder.news_item_layout.setOnClickListener { onClickInterface.onNewsItemClick(news_item.url) }
    }

    inner class NewsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image_view: ImageView
        var title_text_view: TextView
        var news_item_layout : ConstraintLayout

        init {
            title_text_view = view.findViewById<View>(R.id.news_title) as TextView
            image_view = view.findViewById<View>(R.id.news_image) as ImageView
            news_item_layout = view.findViewById<View>(R.id.news_item_layout) as ConstraintLayout
        }
    }
}