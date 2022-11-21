package com.efedaniel.storytablayout.setup.setuptype

interface SetupType {

    var onPageSelected: ((Int) -> Unit)?

    fun getNumberOfTabs(): Int

    fun onCurrentBarFilled(nextIndex: Int)
}
