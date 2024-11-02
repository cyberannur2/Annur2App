package com.miniawradsantri.awrad.bacaan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miniawradsantri.awrad.MainActivity
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentDalailBinding


class DalailFragment : Fragment() {

    private lateinit var binding: FragmentDalailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDalailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.GONE

        val fragmentManager = parentFragmentManager
        binding.apply {
            vAhad.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Ahad", "dalail/dalail_ahad.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vSenin.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Senin", "dalail/dalail_senin.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vSelasa.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Selasa", "dalail/dalail_selasa.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vRabu.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Rabu", "dalail/dalail_rabu.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vKamis.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Kamis", "dalail/dalail_kamis.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vJumat.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Jumat", "dalail/dalail_jumat.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            vSabtu.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(
                        R.id.dalail,
                        BacaanPdf.newInstance("Dalailul Khairat Hari Sabtu", "dalail/dalail_sabtu.pdf"))
                    .addToBackStack(null)
                    .commit()
            }
            btnBack.setOnClickListener {
                Log.d("Button Back", "Button Back icon clicked")
                parentFragmentManager.popBackStack()
            }
        }


    }

    override fun onPause() {
        super.onPause()
        // Mengatur navigation menjadi visible lagi saat fragment tidak aktif
        val mainActivityBinding = (activity as? MainActivity)?.binding
        mainActivityBinding?.navigation?.visibility = View.VISIBLE
    }
}