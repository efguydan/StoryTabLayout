package com.efedaniel.storytablayout.setup

import androidx.viewpager2.widget.ViewPager2

internal class ViewPager2SetupType(
    private val viewPager2: ViewPager2
): SetupType {

    override fun getNumberOfTabs(): Int = viewPager2.adapter?.itemCount ?: 0
}