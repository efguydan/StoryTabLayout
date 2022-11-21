package com.efedaniel.storytablayout.setup.setuptype

import androidx.viewpager2.widget.ViewPager2
import com.efedaniel.storytablayout.extensions.moveToNextPage

internal class ViewPager2SetupType(
    private val viewPager2: ViewPager2
): SetupType, ViewPager2.OnPageChangeCallback() {

    init {
        viewPager2.registerOnPageChangeCallback(this)
    }

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int = viewPager2.adapter?.itemCount ?: 0

    override fun onCurrentBarFilled(nextIndex: Int) {
        viewPager2.moveToNextPage()
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.invoke(position)
    }
}