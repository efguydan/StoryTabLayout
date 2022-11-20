package com.efedaniel.storytablayout.extensions

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

internal fun Int.dpToPixels() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).roundToInt()

