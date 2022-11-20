package com.efedaniel.storytablayout.setup

import androidx.viewpager2.widget.ViewPager2
import com.efedaniel.storytablayout.setup.setuptype.SetupType
import com.efedaniel.storytablayout.setup.setuptype.ViewPager2SetupType

internal interface STLSetup {

    fun setup(type: SetupType)

    fun setupWithViewPager2(viewPager2: ViewPager2) {
        setup(ViewPager2SetupType(viewPager2))
    }
}
