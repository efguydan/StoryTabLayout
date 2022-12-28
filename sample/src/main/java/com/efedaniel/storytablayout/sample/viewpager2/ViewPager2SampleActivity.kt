package com.efedaniel.storytablayout.sample.viewpager2

import android.graphics.Color.BLACK
import android.graphics.Color.BLUE
import android.graphics.Color.CYAN
import android.graphics.Color.GRAY
import android.graphics.Color.GREEN
import android.graphics.Color.MAGENTA
import android.graphics.Color.RED
import android.graphics.Color.WHITE
import android.graphics.Color.YELLOW
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.efedaniel.storytablayout.sample.R
import com.efedaniel.storytablayout.sample.databinding.ActivityViewPager2SampleBinding
import com.efedaniel.storytablayout.sample.exts.onValueChanged

class ViewPager2SampleActivity : AppCompatActivity() {

    val colors = intArrayOf(RED, GREEN, BLUE, BLACK, CYAN, GRAY, MAGENTA, YELLOW) // size = 3

    private lateinit var binding: ActivityViewPager2SampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2SampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupViewPager2()
        setupView()
    }

    private fun setupViewPager2() {
        binding.viewPager.adapter = ViewPager2Adapter(this)
        binding.storyTabLayout.run {
            setupWithViewPager2(binding.viewPager)
            start()
        }
    }

    private fun setupView() = binding.run {
        animateBarSnaps.setOnCheckedChangeListener { _, checked ->
            storyTabLayout.animateBarSnaps = checked
        }

        spacingSeekbar.progress = 4
        spacingSeekbar.onValueChanged { storyTabLayout.barSpacing = it }

        cornerRadiusSeekbar.progress = 4
        cornerRadiusSeekbar.onValueChanged { storyTabLayout.barCornerRadius = it }

        durationSeekbar.progress = 5000
        durationSeekbar.onValueChanged { storyTabLayout.barDuration = it.coerceAtLeast(500) }

        trackColorView.setBackgroundColor(ContextCompat.getColor(this@ViewPager2SampleActivity, R.color.glaucous))
        trackColorView.setOnClickListener {
            MaterialDialog(this@ViewPager2SampleActivity).show {
                title(R.string.choose_color)
                colorChooser(
                    colors = colors,
                    allowCustomArgb = true,
                    showAlphaSelector = true,
                ) { _, color ->
                    trackColorView.setBackgroundColor(color)
                    storyTabLayout.barTrackColor = color
                }
                positiveButton(R.string.select)
            }
        }

        indicatorColorView.setBackgroundColor(ContextCompat.getColor(this@ViewPager2SampleActivity, R.color.glaucous))
        indicatorColorView.setOnClickListener {
            MaterialDialog(this@ViewPager2SampleActivity).show {
                title(R.string.choose_color)
                colorChooser(
                    colors = colors,
                    allowCustomArgb = true,
                    showAlphaSelector = true,
                ) { _, color ->
                    indicatorColorView.setBackgroundColor(color)
                    storyTabLayout.barIndicatorColor = color
                }
                positiveButton(R.string.select)
            }
        }

        start.setOnClickListener { storyTabLayout.start() }

        pause.setOnClickListener { storyTabLayout.pause() }

        resume.setOnClickListener { storyTabLayout.resume() }

        restartCurrent.setOnClickListener { storyTabLayout.restartCurrentTab() }

        stop.setOnClickListener { storyTabLayout.stop(fillAllBars = true) }
    }
}
