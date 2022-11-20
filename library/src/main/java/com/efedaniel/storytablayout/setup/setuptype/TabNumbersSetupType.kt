package com.efedaniel.storytablayout.setup.setuptype

internal class TabNumbersSetupType(
    private val numberOfTabs: Int
): SetupType {

    override fun getNumberOfTabs(): Int  = numberOfTabs
}