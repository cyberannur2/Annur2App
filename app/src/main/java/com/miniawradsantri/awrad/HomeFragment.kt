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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.miniawradsantri.awrad.adapter.ArticleAdapter
import com.miniawradsantri.awrad.agenda.AgendaFragment
import com.miniawradsantri.awrad.artikel.ListArtikelFragment
import com.miniawradsantri.awrad.databinding.FragmentHomeBinding
import com.miniawradsantri.awrad.viewmodel.ArtikelViewModel


class HomeFragment : Fragment() {
    //  mengikat ui dari xml
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtikelViewModel by viewModels()

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

        // Banner Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.view_banner, BannerFragment())
            .addToBackStack(null)
            .commit()

        // Agenda Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.view_agenda, AgendaFragment())
            .addToBackStack(null)
            .commit()

        // Artikel List Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_article_list_container, ListArtikelFragment())
            .commit()

        // Show Bottom Sheet
        binding.includeBacaan.icLainnya.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.layout_semua_menu, null)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(dialogView)
        dialog.show()
    }
}