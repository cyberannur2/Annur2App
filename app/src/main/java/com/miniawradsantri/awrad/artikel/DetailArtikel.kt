package com.miniawradsantri.awrad.artikel

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentDetailArtikelBinding


class DetailArtikel : Fragment() {

    private lateinit var binding: FragmentDetailArtikelBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->

            val articleUrl = bundle.getString("articleUrl")

            // Load URL in WebView
            binding.webviewDetail.apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadsImagesAutomatically = true
                settings.minimumFontSize = 14
                settings.fixedFontFamily = R.font.roboto.toString()
//                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                // Set WebViewClient to handle navigation and inject JavaScript
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        binding.shimmerViewContainer.visibility = View.VISIBLE
                        binding.webviewDetail.visibility = View.INVISIBLE
                    }
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.shimmerViewContainer.stopShimmer()
                        binding.shimmerViewContainer.visibility = View.GONE
                        binding.webviewDetail.visibility = View.VISIBLE
                        // Inject CSS for custom font

                        val js = """
                            (function() {
                                // Inject CSS for custom font size
                                var style = document.createElement('style');
                                style.type = 'text/css';
                                style.innerHTML = 'body { font-size: 40px; }';
                                document.head.appendChild(style);
                                


                                // Hapus elemen yang tidak diperlukan
                                var header = document.querySelector('header');
                                if (header) { header.style.display = 'none'; }
                                var commentBox = document.querySelector('.comment-box-wrap');
                                if (commentBox) { commentBox.style.display = 'none'; }
                                var sitebread = document.querySelector('#site-breadcrumb');
                                if (sitebread) { sitebread.style.display = 'none'; }
                                
                                var sidebar = document.querySelector('.rbc-sidebar.widget-area.sidebar-sticky');
                                if (sidebar) { sidebar.style.display = 'none'; }
                                var bottomMenu = document.querySelector('.wp-bottom-menu');
                                if (bottomMenu) { bottomMenu.style.display = 'none'; }
                                var loftLoader = document.querySelector('.pl-sun');
                                if (loftLoader) { loftLoader.style.display = 'none'; }
                                var likePostWrapper = document.querySelector('.sharedaddy.sd-block.sd-like.jetpack-likes-widget-wrapper.jetpack-likes-widget-loaded');
                                if (likePostWrapper) { likePostWrapper.style.display = 'none'; }
                                var footer = document.querySelector('.footer-logo.footer-section');
                                if (footer) { footer.style.display = 'none'; }
                                var fwrap = document.querySelector('.footer-wrap');
                                if (fwrap) { fwrap.style.display = 'none'; }
                            })();
                        """
                        // Tambahkan penundaan eksekusi JavaScript
                        Handler(Looper.getMainLooper()).postDelayed({
                            view?.evaluateJavascript(js, null)
                        }, 500)
                    }
                }
                loadUrl(articleUrl ?: "")
            }
            binding.btnBack.setOnClickListener {
                Log.d("Button Back", "Button Back icon clicked")
                parentFragmentManager.popBackStack()
            }
            }
        }

//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }


}