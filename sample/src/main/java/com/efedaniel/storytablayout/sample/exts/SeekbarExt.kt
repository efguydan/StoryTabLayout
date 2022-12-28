package com.efedaniel.storytablayout.sample.exts

import android.widget.SeekBar

fun SeekBar.onValueChanged(handler: (Int) -> Unit) {
    setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            handler.invoke(p1)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
        }
    })
}
