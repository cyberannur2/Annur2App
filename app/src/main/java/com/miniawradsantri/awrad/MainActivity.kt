package com.miniawradsantri.awrad

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.miniawradsantri.awrad.artikel.ArtikelActivity
import com.miniawradsantri.awrad.databinding.ActivityMainBinding
import com.miniawradsantri.awrad.tentang.TentangActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationListener()

    }

    private fun navigationListener() {
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_artikel -> {
                    Intent(this, ArtikelActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_tentang -> {
                    Intent(this, TentangActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
    }
}