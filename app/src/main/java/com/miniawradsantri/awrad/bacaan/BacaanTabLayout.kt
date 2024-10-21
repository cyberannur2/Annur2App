package com.miniawradsantri.awrad.bacaan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentBacaanTabLayoutBinding

class BacaanTabLayout : Fragment() {

    private lateinit var binding: FragmentBacaanTabLayoutBinding
    private val DEFAULT_TEXT_SIZE = 16

    private val fragmentList = mutableListOf<Fragment>()
    private val tabTitleList = mutableListOf<String>()

    private lateinit var tabLayoutMediator: TabLayoutMediator

    companion object {
        fun newInstance(title: String, tabTitles: List<String>): BacaanTabLayout {
            val fragment = BacaanTabLayout()
            val args = Bundle()
            args.putString("TITLE", title)
            args.putStringArrayList("TAB_TITLES", ArrayList(tabTitles))
            fragment.arguments = args
            return fragment
        }
    }
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val titles = bundle.getStringArrayList("TAB_TITLES")
            title = bundle.getString("TITLE")
            if (titles != null) {
                tabTitleList.addAll(titles)

                for (text in tabTitleList) {
                    fragmentList.add(createFragmentForTab(text))
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBacaanTabLayoutBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val newTextSize = bundle.getInt("TEXT_SIZE")
            Log.d("BacaanTabLayout", "New text size received: $newTextSize")
            // Loop melalui fragment dalam ViewPager2 dan update ukuran teks
            val fragmentAdapter = binding.viewPager.adapter as TabLayoutAdapter
            for (i in 0 until fragmentAdapter.itemCount) {
                val fragment = childFragmentManager.findFragmentByTag("f$i")
                Log.d("BacaanTabLayout", "Looking for fragment with tag: f$i")
                if (fragment is ItemTabFragment) {
                    fragment.updateTextSize(newTextSize)
                }
            }
        }
        initView()

        binding.title.text = title

        binding.btnEditText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_bacaan_tab_layout, EditTextSize())
                .addToBackStack(null)
                .commit()
        }


    }

    private fun initView() {

        binding.viewPager.adapter = TabLayoutAdapter(requireActivity(), fragmentList)
        Log.d("BacaanTabLayout", "Fragment list count: ${fragmentList.size}")
        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = tabTitleList[position]
            }
        tabLayoutMediator.attach()

    }

    private fun createFragmentForTab(tabTitle: String): Fragment {
        return when (tabTitle) {
            "Yasin" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_yasin)))
            "Tahlil" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_tahlil)))
            "Doa Tahlil" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_doa_tahlil)))
            "Doa Yasin" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_doa_yasin)))
            "Al-Waqiah" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_waqiah)))
            "Doa Waqiah" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_doa_waqiah)))
            "Birrul Walidain" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_birrul)))
            "Istighosah" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_istighosah)))
            "Doa Istighosah" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_doa_istighosah)))
            "Ad-Dukhon" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_dukhon)))
            "Fushilat" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_fushilat)))
            "Al-Hasyr" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_hasyr)))
            "As-Sajdah" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_sajdah)))
            "Al-Mulk" -> ItemTabFragment.newInstance(convertLatinToArabicNumbers(getString(R.string.b_mulk)))
            else -> ItemTabFragment.newInstance("Default")
        }
    }

    fun convertLatinToArabicNumbers(input: String): String {
        val arabicNumbers = arrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
        val result = StringBuilder()

        for (char in input) {
            if (char.isDigit()) {
                val index = char.digitToInt()
                result.append(arabicNumbers[index])
            } else {
                result.append(char)
            }
        }

        return result.toString()
    }

}