package com.miniawradsantri.awrad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miniawradsantri.awrad.adapter.ArticleAdapter
import com.miniawradsantri.awrad.databinding.FragmentHomeBinding
import com.miniawradsantri.awrad.entities.ArticleEntity
import com.miniawradsantri.awrad.model.Article
import com.miniawradsantri.awrad.model.Title
import com.miniawradsantri.awrad.utils.HorizontalSpaceItemDecoration
import com.miniawradsantri.awrad.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    //  mengikat ui dari xml
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

    /**
     * Mengamati LiveData dari ViewModel untuk memperbarui RecyclerView ketika data berubah.
     */
    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            updateRecyclerView(articles)
        })
        viewModel.categoriesMap.observe(viewLifecycleOwner, Observer {
            updateRecyclerView(viewModel.articles.value ?: emptyList())
        })
        viewModel.mediaMap.observe(viewLifecycleOwner, Observer {
            updateRecyclerView(viewModel.articles.value ?: emptyList())
        })
    }

    /**
     * Memperbarui RecyclerView dengan data terbaru.
     *
     * parameter articles Daftar objek ArticleEntity yang mewakili artikel.
     */
    private fun updateRecyclerView(articles: List<ArticleEntity>) {
        val categoriesMap = viewModel.categoriesMap.value ?: emptyMap()
        val mediaMap = viewModel.mediaMap.value ?: emptyMap()
        // Mengubah objek ArticleEntity menjadi model Article yang diperlukan oleh adapter
        val articleList = articles.map { article ->
            Article(
                id = article.id,
                title = Title(article.title),
                categories = article.categories,
                featured_media = article.featured_media,

            )
        }
        // Inisialisasi ArticleAdapter baru dengan data yang diperbarui dan mengaturnya ke RecyclerView
        binding.rvItemArticle.adapter = ArticleAdapter(articleList, categoriesMap, mediaMap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}