package com.efedaniel.storytablayout.setup.setuptype

import androidx.viewpager.widget.ViewPager

internal class ViewPagerSetupType(
    private val viewPager: ViewPager
): SetupType {

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int = viewPager.adapter?.count ?: 0

    override fun onCurrentBarFilled(nextIndex: Int) {
        TODO("Not yet implemented")
    }
}