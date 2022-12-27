package com.efedaniel.storytablayout.sample.exts

import android.graphics.Color
import java.util.Random

fun getRandomLightColor(): Int {
    val random = Random()
    val red = 200 + random.nextInt(30);
    val green = 200 + random.nextInt(30);
    val blue = 200 + random.nextInt(30);
    return Color.rgb(red, green, blue)
}
