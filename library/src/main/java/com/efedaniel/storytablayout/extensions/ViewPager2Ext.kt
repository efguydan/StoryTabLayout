package com.efedaniel.storytablayout.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.moveToNextPage(smoothScroll: Boolean = true): Boolean {
    val totalPages = adapter?.itemCount ?: 0
    val nextPage = ++currentItem

    return if (nextPage < totalPages) {
        setCurrentItem(nextPage, smoothScroll)
        true
    } else {
        false
    }
}
