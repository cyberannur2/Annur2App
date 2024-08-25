package com.miniawradsantri.awrad.artikel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentDetailArtikelBinding


class DetailArtikel : Fragment() {

    private var _binding: FragmentDetailArtikelBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val title = bundle.getString("title")
            val content = bundle.getString("content")
            val imageUrl = bundle.getString("imageUrl")
            val category = bundle.getString("category")

            binding.tvTitleDetail.text = title
            binding.tvContent.text = content

            Glide.with(this)
                .load(imageUrl)
                .into(binding.ivImageDetail)
            category?.let {
                binding.categoryContainerDetail.removeAllViews()

                it.forEach { categoryName ->

                    val textView = TextView(requireContext()).apply {
                        text = categoryName.toString()
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
                    binding.categoryContainerDetail.addView(textView, params)
                }
                }

            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}