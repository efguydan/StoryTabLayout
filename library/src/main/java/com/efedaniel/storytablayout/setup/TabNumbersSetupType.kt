package com.efedaniel.storytablayout.setup

internal class TabNumbersSetupType(
    private val numberOfTabs: Int
): SetupType {

    override fun getNumberOfTabs(): Int  = numberOfTabs
}