package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
    }
}