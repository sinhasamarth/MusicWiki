package com.samarth.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.databinding.ActivityMainBinding
import com.samarth.musicapp.databinding.FragmentHomeBinding
import com.samarth.musicapp.view.adapters.TopGenresAdapter
import com.samarth.musicapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//         navController = navHostFragment.findNavController()
//        if (supportActionBar != null) {
//            supportActionBar!!.hide()
//        }
//        setupActionBarWithNavController(navController)


//        val navHostFragment = binding.fragmentContainerView as NavHostFragment
//        val navController = navHostFragment.navController
//        viewModel.getAllGenres()
    }
}