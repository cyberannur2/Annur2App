package com.miniawradsantri.awrad.bacaan

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miniawradsantri.awrad.databinding.FragmentTestBinding


class ItemTabFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    private val DEFAULT_TEXT_SIZE = 16

    companion object {
        fun newInstance(text: String): ItemTabFragment {
            val fragment = ItemTabFragment()
            val args = Bundle()
            args.putString("TEXT", text)
            fragment.arguments = args
            return fragment
        }
    }

    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString("TEXT")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val newTextSize = bundle.getInt("TEXT_SIZE")
            binding.textBacaan.setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize.toFloat())
        }
        binding.textBacaan.text = text
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
        binding.textBacaan.setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize.toFloat())
    }

}