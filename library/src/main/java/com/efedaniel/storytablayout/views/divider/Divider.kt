package com.efedaniel.storytablayout.views.divider

import android.graphics.drawable.ShapeDrawable
import com.efedaniel.storytablayout.extensions.dpToPixels

internal class Divider(
    sizeInDp: Int
): ShapeDrawable() {

    init {
        intrinsicWidth = sizeInDp.dpToPixels()
        alpha = 0
    }
}