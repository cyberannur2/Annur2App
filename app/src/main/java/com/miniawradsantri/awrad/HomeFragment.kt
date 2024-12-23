package com.miniawradsantri.awrad

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.replace
import com.miniawradsantri.awrad.banner.BannerFragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.miniawradsantri.awrad.agenda.AgendaFragment
import com.miniawradsantri.awrad.artikel.ListArtikelFragment
import com.miniawradsantri.awrad.bacaan.BacaanPdf
import com.miniawradsantri.awrad.bacaan.BacaanTabLayout
import com.miniawradsantri.awrad.bacaan.BacaanSinglePage
import com.miniawradsantri.awrad.bacaan.DalailFragment
import com.miniawradsantri.awrad.databinding.FragmentHomeBinding
import com.miniawradsantri.awrad.databinding.FragmentMenuBacaanBinding
import com.miniawradsantri.awrad.viewmodel.ArtikelViewModel


class HomeFragment : Fragment() {
    //  mengikat ui dari xml
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ArtikelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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
            .commit()

        // Agenda Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.view_agenda, AgendaFragment())
            .commit()

        // Artikel List Fragment
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_article_list_container, ListArtikelFragment())
            .commit()

        // Show Bottom Sheet
        binding.includeBacaan.icLainnya.setOnClickListener {
            showBottomSheet()
        }

        // Tawassul
        binding.includeBacaan.icTawassul.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanSinglePage.newInstance(
                        getString(R.string.tawassul),
                        getString(R.string.b_tawassul)
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Yasin Tahlil
        binding.includeBacaan.icYasinTahlil.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Yasin", "Tahlil", "Doa Yasin", "Doa Tahlil")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.yasin_tahlil),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Al-Waqiah
        binding.includeBacaan.icWaqiah.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Al-Waqiah", "Doa Waqiah")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.al_waqiah),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Birrul Walidain
        binding.includeBacaan.icBirrulWalidain.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanSinglePage.newInstance(
                        getString(R.string.birrul_walidain),
                        getString(R.string.b_birrul)
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Istighosah
        binding.includeBacaan.icIstighosah.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Istighosah", "Doa Istighosah")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.istighosah),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Burdah
        binding.includeBacaan.icBurdah.setOnClickListener {
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanPdf.newInstance("Qasidah Burdah", "burdah/burdah.pdf")
                )
                .addToBackStack(null)
                .commit()
        }

        // Maulid Diba
        binding.includeBacaan.icDiba.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf(
                "Ya Rabbi Shalli...",
                "Laqod Jaakum...",
                "Ya Rasulallah...",
                "Al-hamdulillahil Qawiyy...",
                "Qila Huwa Adam...",
                "Yub'atsu Min Tihamah...",
                "Tsumma Arudduhu...",
                "Shalatullahi Ma Lahat...",
                "Fasubhanaman Khashahu...",
                "Awwaluma Nastaftihu...",
                "Al-haditsul Awwal...",
                "Al-haditsus Tsani...",
                "Fayaqulul Haqqu...",
                "Ahdliru Qulubakum...",
                "Fahtazzal Arsyu...",
                "Mahallul Qiyam",
                "Wawulida Shallallahu...",
                "Qila Man Yakfulu...",
                "Tsumma A'radla...",
                "Fabainama Huwa...",
                "Faqolatil Malaikatu...",
                "Fabainama Habibu...",
                "Falamma Ra`athu...",
                "Wakana Shallallahu...",
                "Waqila Liba'dhihim...",
                "Wama 'Asa...",
                "Ya Badratim...",
                "Doa Maulid Diba'"
                )
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.maulid_diba),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

//        // Sab'ul Munjiyat
//        binding.includeBacaan.icSabulMunjiyat.setOnClickListener {
//            val bacaanTabLayout = BacaanTabLayout()
//            val tabTitles = arrayListOf(
//                "As-Sajdah",
//                "Yasin",
//                "Fushilat",
//                "Ad-Dukhon",
//                "Al-Waqiah",
//                "Al-Hasyr",
//                "Al-Mulk"
//            )
//            val bundle = Bundle()
//            bundle.putStringArrayList("TAB_TITLES", tabTitles)
//            bacaanTabLayout.arguments = bundle
//            fragmentManager.beginTransaction()
//                .replace(
//                    R.id.frame_home,
//                    BacaanTabLayout.newInstance(
//                        getString(R.string.sab_ul_munjiyat),
//                        tabTitles
//                    )
//                )
//                .addToBackStack(null)
//                .commit()
//        }

    }

    private fun showBottomSheet() {
        val bindingBottomSheet = FragmentMenuBacaanBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        val fragmentManager = childFragmentManager

        dialog.setContentView(bindingBottomSheet.root)
        dialog.show()

        // Tawassul
        bindingBottomSheet.icTawassul.setOnClickListener {
            Log.d("MenuBacaan", "Tawassul icon clicked")
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanSinglePage.newInstance(
                        getString(R.string.tawassul),
                        getString(R.string.b_tawassul)
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Yasin Tahlil
        bindingBottomSheet.icYasinTahlil.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Yasin", "Tahlil", "Doa Yasin", "Doa Tahlil")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.yasin_tahlil),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Al-Waqiah
        bindingBottomSheet.icWaqiah.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Al-Waqiah", "Doa Waqiah")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.al_waqiah),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Birrul Walidain
        bindingBottomSheet.icBirrulWalidain.setOnClickListener {
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanSinglePage.newInstance(
                        getString(R.string.birrul_walidain),
                        getString(R.string.b_birrul)
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Istighosah
        bindingBottomSheet.icIstighosah.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf("Istighosah", "Doa Istighosah")
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.istighosah),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Burdah
        bindingBottomSheet.icBurdah.setOnClickListener {
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanPdf.newInstance("Qasidah Burdah", "burdah/burdah.pdf")
                )
                .addToBackStack(null)
                .commit()
        }

        // Maulid Diba
        bindingBottomSheet.icDiba.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf(
                "Ya Rabbi Shalli...",
                "Laqod Jaakum...",
                "Ya Rasulallah...",
                "Al-hamdulillahil Qawiyy...",
                "Qila Huwa Adam...",
                "Yub'atsu Min Tihamah...",
                "Tsumma Arudduhu...",
                "Shalatullahi Ma Lahat...",
                "Fasubhanaman Khashahu...",
                "Awwaluma Nastaftihu...",
                "Al-haditsul Awwal...",
                "Al-haditsus Tsani...",
                "Fayaqulul Haqqu...",
                "Ahdliru Qulubakum...",
                "Fahtazzal Arsyu...",
                "Mahallul Qiyam",
                "Wawulida Shallallahu...",
                "Qila Man Yakfulu...",
                "Tsumma A'radla...",
                "Fabainama Huwa...",
                "Faqolatil Malaikatu...",
                "Fabainama Habibu...",
                "Falamma Ra`athu...",
                "Wakana Shallallahu...",
                "Waqila Liba'dhihim...",
                "Wama 'Asa...",
                "Ya Badratim...",
                "Doa Maulid Diba'"
            )
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.maulid_diba),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Sab'ul Munjiyat
        bindingBottomSheet.icSabulMunjiyat.setOnClickListener {
            val bacaanTabLayout = BacaanTabLayout()
            val tabTitles = arrayListOf(
                "As-Sajdah",
                "Yasin",
                "Fushilat",
                "Ad-Dukhon",
                "Al-Waqiah",
                "Al-Hasyr",
                "Al-Mulk"
            )
            val bundle = Bundle()
            bundle.putStringArrayList("TAB_TITLES", tabTitles)
            bacaanTabLayout.arguments = bundle
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    BacaanTabLayout.newInstance(
                        getString(R.string.sab_ul_munjiyat),
                        tabTitles
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        // Dalail
        bindingBottomSheet.icDalail.setOnClickListener {
            dialog.dismiss()
            fragmentManager.beginTransaction()
                .replace(
                    R.id.frame_home,
                    DalailFragment()
                )
                .addToBackStack(null)
                .commit()
        }
    }


}