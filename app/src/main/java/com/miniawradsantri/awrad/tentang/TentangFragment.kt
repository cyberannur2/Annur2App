package com.miniawradsantri.awrad.tentang

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miniawradsantri.awrad.R
import com.miniawradsantri.awrad.databinding.FragmentTentangBinding


class TentangFragment : Fragment() {

    private lateinit var binding: FragmentTentangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTentangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vIg.setOnClickListener {
            val instagramUrl = "https://www.instagram.com/annur2malang/"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(instagramUrl)
            }
            startActivity(intent)
        }

        binding.vYt.setOnClickListener {
            val youtubeUrl = "https://www.youtube.com/@annur2malangofficial"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(youtubeUrl)
            }
            startActivity(intent)
        }

        binding.vTt.setOnClickListener {
            val tiktokUrl = "https://www.tiktok.com/@annur2malang"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(tiktokUrl)
            }
            startActivity(intent)
        }

        binding.vWeb.setOnClickListener {
            val webUrl = "https://annur2.net/"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(webUrl)
            }
            startActivity(intent)
        }

        binding.vWa.setOnClickListener {
            val webUrl = "https://api.whatsapp.com/send/?phone=6285822221979&text&type=phone_number&app_absent=0"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(webUrl)
            }
            startActivity(intent)
        }

        binding.vCekUpdate.setOnClickListener {
            val webUrl = "https://play.google.com/store/apps/details?id=com.miniawradsantri.awrad"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(webUrl)
            }
            startActivity(intent)
        }
    }

}