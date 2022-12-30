package com.efedaniel.storytablayout.views.divider

import android.graphics.drawable.ShapeDrawable

internal class Divider(
    sizeInPixels: Int
): ShapeDrawable() {

    init {
        intrinsicWidth = sizeInPixels
        alpha = 0
    }
}
