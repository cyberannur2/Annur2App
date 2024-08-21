package com.miniawradsantri.awrad.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val categories: List<Int>, // Menyimpan ID kategori sebagai string yang dipisahkan koma
    val featured_media: Int,

)

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(tableName = "tanggal")
data class TanggalEntity(
    @PrimaryKey val id: Int,
    val tanggal: String
)

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey val id: Int,
    val source_url: String
)
