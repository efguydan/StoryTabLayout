package com.efedaniel.storytablayout.extensions

import com.google.android.material.progressindicator.LinearProgressIndicator

fun LinearProgressIndicator.isFilled(): Boolean = progress == max
