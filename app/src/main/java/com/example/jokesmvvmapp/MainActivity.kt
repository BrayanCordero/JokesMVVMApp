package com.example.jokesmvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jokesmvvmapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navigationBar
        val navController = findNavController(R.id.nav_jokes_container)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_change_name_fragment, R.id.navigation_never_ending_fragment, R.id.navigation_random_joke_fragment
            )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}