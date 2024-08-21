package com.miniawradsantri.awrad.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.miniawradsantri.awrad.repository.AppDatabase
import com.miniawradsantri.awrad.data.WordPressApi
import com.miniawradsantri.awrad.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appDao = AppDatabase.getDatabase(application).appDao()

    val articles: LiveData<List<ArticleEntity>> = appDao.getAllArticles()
    private val categories = appDao.getAllCategories()
    private val media = appDao.getAllMedia()

    val categoriesMap: LiveData<Map<Int, String>> = categories.map { list ->
        val map = list.associate { it.id to it.name }
        Log.d("MainViewModel", "categoriesMap: $map")
        map
    }

    val mediaMap: LiveData<Map<Int, String>> = media.map { list ->
        list.associate { it.id to it.source_url }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://annur2.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(WordPressApi::class.java)

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { api.getCategories(perPage = 70).execute() }
                if (response.isSuccessful) {
                    val categories = response.body() ?: emptyList()
                    val categoryEntities = categories.map { CategoryEntity(it.id, it.name) }
                    appDao.insertCategories(categoryEntities)
                }else{
                    Log.e("MainViewModel", "Gagal mengambil kategori. Response code: ${response.code()}")
                }
                fetchArticles()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Gagal Mengambil kategori: ${e.message}")
            }
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { api.getArticles(3).execute() }
                if (response.isSuccessful) {
                    val articlesResponse = response.body() ?: emptyList()
                    val mediaMapTemp = mutableListOf<MediaEntity>()
                    val articleEntities = articlesResponse.map { article ->
                        val mediaResponse = withContext(Dispatchers.IO) { api.getMedia(article.featured_media).execute() }
                        if (mediaResponse.isSuccessful) {
                            mediaResponse.body()?.let { media ->
                                mediaMapTemp.add(MediaEntity(article.featured_media, media.source_url))
                            }
                        } else {
                            Log.e("MainViewModel", "Gagal untuk mengambil media ${article.id}. Error code: ${mediaResponse.code()}")
                        }
                        ArticleEntity(article.id, article.title.rendered, article.categories, article.featured_media)
                    }
                    appDao.insertArticles(articleEntities)
                    appDao.insertMedia(mediaMapTemp)
                    Log.d("MainViewModel", "Berhasil mengambil data artikel dan media.")
                } else {
                    Log.e("MainViewModel", "Gagal untuk mengambil artikel. Response code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Gagal untuk mengambil artikel: ${e.message}")
            }
        }
    }
}