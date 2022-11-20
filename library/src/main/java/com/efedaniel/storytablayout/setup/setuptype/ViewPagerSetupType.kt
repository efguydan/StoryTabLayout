package com.efedaniel.storytablayout.setup.setuptype

import androidx.viewpager.widget.ViewPager

internal class ViewPagerSetupType(
    private val viewPager: ViewPager
): SetupType {

    override fun getNumberOfTabs(): Int = viewPager.adapter?.count ?: 0
}