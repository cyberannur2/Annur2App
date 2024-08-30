package com.miniawradsantri.awrad.artikel

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        // Setup Toolbar
        val toolbar = binding.root.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            // Handle back navigation
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        arguments?.let { bundle ->
            val title = bundle.getString("title")
            val content = bundle.getString("content")
            val imageUrl = bundle.getString("imageUrl")
            val category = bundle.getStringArrayList("category")

            binding.tvTitleDetail.text = title
//            binding.tvContent.text= Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
//            binding.tvContent.movementMethod = LinkMovementMethod.getInstance()

            // Load content in WebView
            binding.webviewDetail.apply {
                settings.javaScriptEnabled = true
//                webViewClient = WebViewClient()
                loadDataWithBaseURL(null, content ?: "", "text/html", "UTF-8", null)
            }

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

//            val articleUrl = bundle.getString("articleUrl")
//
//            // Load URL in WebView
//            binding.webviewDetail.apply {
//                settings.javaScriptEnabled = true
//                settings.domStorageEnabled = true
//                settings.loadsImagesAutomatically = true
//                // Set WebViewClient to handle navigation and inject JavaScript
//                webViewClient = object : WebViewClient() {
//                    override fun onPageFinished(view: WebView?, url: String?) {
//                        super.onPageFinished(view, url)
//                        // Inject JavaScript to remove header
//                        view?.loadUrl(
//                            "javascript:(function() { " +
//                                    "var header = document.querySelector('header');" +
//                                    "if(header) { header.style.display='none'; }" +
//                                    "var commentBox = document.querySelector('.comment-box-wrap');" +
//                                    "if(commentBox) { commentBox.style.display='none'; }" +
//                                    "var sidebar = document.querySelector('.rbc-sidebar.widget-area.sidebar-sticky');" +
//                                    "if(sidebar) { sidebar.style.display='none'; }" +
//                                    "})()"
//                        )
//                    }
//                }
//                loadUrl(articleUrl ?: "")
//            }

            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}