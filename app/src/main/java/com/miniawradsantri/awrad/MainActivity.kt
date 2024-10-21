package com.miniawradsantri.awrad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.miniawradsantri.awrad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        val navView: BottomNavigationView = binding.navigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

    }

//    override fun onBackPressed() {
//        val fragmentManager = supportFragmentManager
//        if (fragmentManager.backStackEntryCount > 0) {
//            // Jika ada fragment di back stack, pop fragment tersebut
//            fragmentManager.popBackStack()
//        } else {
//            // Jika tidak ada fragment di back stack, keluar dari aplikasi
//            super.onBackPressed()
//        }
//    }


}