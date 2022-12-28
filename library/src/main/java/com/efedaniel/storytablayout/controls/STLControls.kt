package com.efedaniel.storytablayout.controls

interface STLControls {

    fun start()

    fun pause()

    fun resume()

    fun stop(fillAllBars: Boolean)

    fun restartCurrentTab()

    fun jumpToPage(index: Int)
}
