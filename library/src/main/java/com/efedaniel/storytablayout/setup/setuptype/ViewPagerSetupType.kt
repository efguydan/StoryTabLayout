package com.efedaniel.storytablayout.setup.setuptype

import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

internal class ViewPagerSetupType(
    private val viewPager: ViewPager
): SetupType, OnPageChangeListener {

    init {
        viewPager.addOnPageChangeListener(this)
    }

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int = viewPager.adapter?.count ?: 0

    override fun onCurrentBarFilled(nextIndex: Int) {
        viewPager.setCurrentItem(nextIndex, true)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // Do Nothing
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.invoke(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        // Do Nothing
    }
}
