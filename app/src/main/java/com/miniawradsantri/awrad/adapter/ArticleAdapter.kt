package com.miniawradsantri.awrad.adapter

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.miniawradsantri.awrad.databinding.ItemArticle1Binding
import com.miniawradsantri.awrad.model.Article
import com.bumptech.glide.Glide
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.ItemArtikelBinding

class ArticleAdapter(
    private val articles: List<Article>,
    private val categoriesMap: Map<Int, String>,
    private val mediaMap: Map<Int, String>
): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(val binding: ItemArtikelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.tvTitle.text = article.title.rendered
        // Clear any existing views in the category container
        holder.binding.categoryContainer.removeAllViews()

        // Add a TextView for each category
        article.categories.forEach { categoryId ->
            val categoryName = categoriesMap[categoryId] ?: "Unknown"
            val textView = TextView(holder.itemView.context).apply {
                text = categoryName
                setTextColor(ContextCompat.getColor(context, R.color.white))
                setBackgroundResource(R.drawable.bg_artikel_tag)
                setPadding(12, 4, 12, 4)
                textSize = 10f
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(15, 8, 8, 8) // Adjust margins as necessary
            holder.binding.categoryContainer.addView(textView, params)
        }



        Glide.with(holder.itemView.context)
            .load(mediaMap[article.featured_media])
            .into(holder.binding.ivImage)
    }

}