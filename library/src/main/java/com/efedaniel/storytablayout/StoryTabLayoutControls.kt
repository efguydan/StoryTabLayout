package com.efedaniel.storytablayout

interface StoryTabLayoutControls {

    fun start()

    fun pause()

    fun stop()

    fun restart()

    fun restartCurrentTab()

    fun jumpToPage(index: Int): Boolean
}