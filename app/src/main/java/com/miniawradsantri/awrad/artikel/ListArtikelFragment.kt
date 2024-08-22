package com.miniawradsantri.awrad.artikel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.adapter.ArticleAdapter
import com.miniawradsantri.awrad.databinding.FragmentListArtikelBinding
import com.miniawradsantri.awrad.entities.ArticleEntity
import com.miniawradsantri.awrad.model.Article
import com.miniawradsantri.awrad.model.Title
import com.miniawradsantri.awrad.utils.HorizontalSpaceItemDecoration
import com.miniawradsantri.awrad.viewmodel.MainViewModel



class ListArtikelFragment : Fragment() {
    private var _binding: FragmentListArtikelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListArtikelBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        binding.rvItemArticle.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvItemArticle.addItemDecoration(HorizontalSpaceItemDecoration(10))
    }

    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            updateRecyclerView(articles)
            if (articles.isNotEmpty()){
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.categoriesMap.observe(viewLifecycleOwner, Observer {
            updateRecyclerView(viewModel.articles.value ?: emptyList())
        })
        viewModel.mediaMap.observe(viewLifecycleOwner, Observer {
            updateRecyclerView(viewModel.articles.value ?: emptyList())
        })
    }

    private fun updateRecyclerView(articles: List<ArticleEntity>) {
        val categoriesMap = viewModel.categoriesMap.value ?: emptyMap()
        val mediaMap = viewModel.mediaMap.value ?: emptyMap()
        val articleList = articles.map { article ->
            Article(
                id = article.id,
                title = Title(article.title),
                categories = article.categories,
                featured_media = article.featured_media,
                date = article.date
            )
        }
        binding.rvItemArticle.adapter = ArticleAdapter(articleList, categoriesMap, mediaMap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}