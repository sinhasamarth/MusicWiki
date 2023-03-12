package com.samarth.musicapp.view.adapters.genersDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.samarth.musicapp.view.fragment.genreFragments.AlbumFragments
import com.samarth.musicapp.view.fragment.genreFragments.ArtistFragment
import com.samarth.musicapp.view.fragment.TracksFragment

class GenreViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumFragments()
            1 -> ArtistFragment()
            else -> TracksFragment()
        }
    }

}