package com.efedaniel.storytablayout.sample.exts

import android.graphics.Color
import java.util.Random

fun getRandomColor(): Int {
    val random = Random()
    val red = 30 + random.nextInt(200);
    val green = 30 + random.nextInt(200);
    val blue = 30 + random.nextInt(200);
    return Color.rgb(red, green, blue)
}