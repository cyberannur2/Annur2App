package com.miniawradsantri.awrad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miniawradsantri.awrad.banner.BannerFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miniawradsantri.awrad.adapter.ArticleAdapter
import com.miniawradsantri.awrad.artikel.ListArtikelFragment
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentManager = childFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.view_banner, BannerFragment())
            .addToBackStack(null)
            .commit()
        // Artikel List Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_article_list_container, ListArtikelFragment())
            .commit()
    }



}