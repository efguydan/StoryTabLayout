package com.efedaniel.storytablayout.setup.setuptype

internal class TabNumbersSetupType(
    private val numberOfTabs: Int
): SetupType {

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int  = numberOfTabs

    override fun onCurrentBarFilled(nextIndex: Int) {
        TODO("Not yet implemented")
    }
}