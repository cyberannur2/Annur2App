package com.miniawradsantri.awrad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.miniawradsantri.awrad.databinding.ActivityMainBinding
import com.miniawradsantri.awrad.utils.lightStatusBar
import com.miniawradsantri.awrad.utils.setFullScreen

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen(window)
        lightStatusBar(window)

        FirebaseApp.initializeApp(this)

        val navView: BottomNavigationView = binding.navigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

    }

}