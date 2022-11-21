package com.efedaniel.storytablayout.sample.viewpager2

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.efedaniel.storytablayout.sample.pagerfragment.PagerFragment

class ViewPager2Adapter(
    activity: FragmentActivity,
    private val numItems: Int = 10
): FragmentStateAdapter(activity) {

    override fun getItemCount() = numItems

    override fun createFragment(position: Int) = PagerFragment.newInstance(position+1)
}