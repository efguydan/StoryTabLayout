package com.efedaniel.storytablayout.sample.viewpager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.efedaniel.storytablayout.sample.pagerfragment.PagerFragment

class ViewPagerAdapter(
    fm: FragmentManager,
    private val numItems: Int = 10
) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = numItems

    override fun getItem(position: Int) = PagerFragment.newInstance(position+1)
}
