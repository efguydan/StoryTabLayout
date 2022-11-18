package com.efedaniel.storytablayout

interface STLControls {

    fun start()

    fun pause()

    fun stop()

    fun restart()

    fun restartCurrentTab()

    fun jumpToPage(index: Int): Boolean
}