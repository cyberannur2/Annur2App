package com.miniawradsantri.awrad.bacaan

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.miniawradsantri.awrad.MainActivity
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.ActivityMainBinding
import com.miniawradsantri.awrad.databinding.FragmentBacaanSinglePageBinding


class BacaanSinglePage : Fragment() {
    private lateinit var binding: FragmentBacaanSinglePageBinding
    private val DEFAULT_TEXT_SIZE = 16

    companion object {
        fun newInstance(title: String, text: String): BacaanSinglePage {
            val fragment = BacaanSinglePage()
            val args = Bundle()
            args.putString("TITLE", title)
            args.putString("TEXT", text)
            fragment.arguments = args
            return fragment
        }
    }
    private var title: String? = null
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("TITLE")
            text = it.getString("TEXT")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBacaanSinglePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.GONE


        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val newTextSize = bundle.getInt("TEXT_SIZE")
            binding.txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize.toFloat())
        }
        binding.btnEditText.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_bacaan_single_page, EditTextSize())
                .addToBackStack(null)
                .commit()
        }

        binding.btnBack.setOnClickListener {
            Log.d("Button Back", "Button Back icon clicked")
            parentFragmentManager.popBackStack()
        }

        binding.title.text = title
        binding.txt.text = text
    }
    override fun onResume() {
        super.onResume()

        val savedTextSize = loadTextSize()
        updateTextSize(savedTextSize)
    }

    private fun loadTextSize(): Int {
        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs_TextSize", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("TEXT_SIZE", DEFAULT_TEXT_SIZE)
    }

    fun updateTextSize(newTextSize: Int) {
        Log.d("TestFragment", "Updating text size to: $newTextSize")
        binding.txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize.toFloat())
    }
    override fun onPause() {
        super.onPause()
        // Mengatur navigation menjadi visible lagi saat fragment tidak aktif
        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.VISIBLE
    }
}