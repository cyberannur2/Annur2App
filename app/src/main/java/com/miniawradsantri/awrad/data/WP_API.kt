package com.miniawradsantri.awrad.data

import com.miniawradsantri.awrad.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordPressApi {
    @GET("wp-json/wp/v2/posts")
    fun getArticles(@Query("per_page") perPage: Int): Call<List<Article>>

    @GET("wp-json/wp/v2/media/{id}")
    fun getMedia(@Path("id") id: Int): Call<Media>

    @GET("wp-json/wp/v2/categories")
    fun getCategories(@Query("per_page")perPage: Int): Call<List<Category>>
}