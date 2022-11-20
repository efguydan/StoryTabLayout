package com.efedaniel.storytablayout.setup

import androidx.viewpager2.widget.ViewPager2

internal interface STLSetup {

    fun setup(type: SetupType)

    fun setupWithViewPager2(viewPager2: ViewPager2) {
        setup(ViewPager2SetupType(viewPager2))
    }
}
