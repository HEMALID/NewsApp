package com.example.newsapp.recycler2

import com.example.newsapp.recycler2.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

   /* https://inshorts.deta.dev/news?category={category_name}*/
    /* https://inshorts.deta.dev/news?category=technology */

    @GET("news")
    fun getData2(@Query("category") category: String):Call<Data>

   /* */
}