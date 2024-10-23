package com.miniawradsantri.awrad.bacaan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.storage.FirebaseStorage
import com.miniawradsantri.awrad.MainActivity
import com.miniawradsantri.awrad.databinding.FragmentBacaanPdfBinding
import java.io.File

class BacaanPdf : Fragment() {

    private lateinit var binding: FragmentBacaanPdfBinding
    private lateinit var pdfView: PDFView

    companion object {
        fun newInstance(title: String, path: String): BacaanPdf {
            val fragment = BacaanPdf()
            val args = Bundle()
            args.putString("TITLE", title)
            args.putString("PATH", path)
            fragment.arguments = args
            return fragment
        }
    }

    private var title: String? = null
    private var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("TITLE")
            path = it.getString("PATH")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBacaanPdfBinding.inflate(inflater, container, false)

        pdfView = binding.pdfView

        fetchPdfUrl()
        binding.title.text = title
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.GONE
        binding.btnBack.setOnClickListener {
            Log.d("Button Back", "Button Back icon clicked")
            parentFragmentManager.popBackStack()
        }
    }
    private fun fetchPdfUrl() {
        Log.d("BacaanPdf", "Fetching PDF URL")
        val pdfRef = FirebaseStorage.getInstance().reference.child(path!!)

        pdfRef.downloadUrl.addOnSuccessListener { uri ->
            val pdfUrl = uri.toString()
            Log.d("YourActivity", "PDF URL obtained: $pdfUrl")
            loadPdf(pdfUrl)
        }.addOnFailureListener { exception ->
            Log.e("FirebaseStorage", "Failed to get PDF URL: ${exception.message}")
        }
    }

    private fun loadPdf(pdfUrl: String) {
        binding.pdfProgressBar.progress = View.VISIBLE
        Log.d("PdfViewerFragment", "Starting download for PDF: $pdfUrl")
        // Download file PDF ke cache
        PRDownloader.download(pdfUrl, requireContext().cacheDir.path, "temp.pdf")
            .build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    binding.pdfProgressBar.progress = View.GONE
                    Log.d("PdfViewerFragment", "PDF download completed.")
                    val file = File(requireContext().cacheDir, "temp.pdf")
                    // Load PDF file ke dalam PDFView
                    if (file.exists()) {
                        Log.d("BacaanPdf", "PDF file exists at: ${file.absolutePath}")
                        pdfView.fromFile(file)
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .load()
                    } else {
                        Log.e("BacaanPdf", "PDF file does not exist.")
                    }
                }

                override fun onError(error: com.downloader.Error?) {
                    binding.pdfProgressBar.progress = View.GONE
                    Log.e("PDF Download", "Error: ${error?.serverErrorMessage}")
                }
            })
    }

    override fun onPause() {
        super.onPause()
        // Mengatur navigation menjadi visible lagi saat fragment tidak aktif
        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.VISIBLE
    }

}