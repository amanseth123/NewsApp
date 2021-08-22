package com.example.newsreaderapp.network;

import com.example.newsreaderapp.models.TopHeadlinesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiList {
    @GET("top-headlines?apiKey=2067accbe67c4a2d8b4a8dc79b9545db")
    Call<TopHeadlinesResponse> topHeadlines(@Query("country") String country);
}
