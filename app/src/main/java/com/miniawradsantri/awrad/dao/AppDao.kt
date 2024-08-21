package com.miniawradsantri.awrad.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miniawradsantri.awrad.entities.*

@Dao
interface AppDao {
    @Query("SELECT * FROM articles ORDER by id DESC")
    fun getAllArticles(): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun clearArticles()


    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories")
    suspend fun clearCategories()


    @Query("SELECT * FROM media")
    fun getAllMedia(): LiveData<List<MediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<MediaEntity>)

    @Query("DELETE FROM media")
    suspend fun clearMedia()

//    @Query("SELECT * FROM tanggal")
//    fun getAllTanggal(): LiveData<List<TanggalEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTanggal(tanggal: List<TanggalEntity>)
//
//    @Query("DELETE FROM tanggal")
//    suspend fun clearTanggal()
}


