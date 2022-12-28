package com.efedaniel.storytablayout.views.automaticprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.animation.doOnEnd
import com.efedaniel.storytablayout.extensions.dpToPixels
import com.efedaniel.storytablayout.extensions.isFilled
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.FILLED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.PAUSED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.RESUMED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.STARTED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.UNFILLED
import com.google.android.material.progressindicator.LinearProgressIndicator

internal class AutomaticProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val listener: AutomaticProgressBarListener? = null,
    private val totalDuration: Int,
    private val cornerRadius: Int,
    @ColorInt private val trackColor: Int? = null,
    @ColorInt private val indicatorColor: Int? = null,
) : FrameLayout(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {

    private companion object {
        const val ANIMATOR_MAX_VALUE = 1000
    }

    private val progressBar = LinearProgressIndicator(context, attrs, defStyleAttr)

    var state: AutomaticProgressBarState = UNFILLED
        set(value) {
            field = value
            when (value) {
                UNFILLED -> unFillProgressBar()
                STARTED -> startProgressBar()
                PAUSED -> pauseProgressBar()
                RESUMED -> resumeProgressBar()
                FILLED -> fillProgressBar()
            }
        }

    var animateSnaps: Boolean = false

    private val animator by lazy {
        ValueAnimator.ofInt(0, ANIMATOR_MAX_VALUE)
    }

    init {
        setupProgressBar()
        setupAnimator()
    }

    private fun setupProgressBar() {
        progressBar.max = totalDuration
        setCornerRadius(cornerRadius)
        setBarTrackColor(trackColor)
        setBarIndicatorColor(indicatorColor)
        addView(progressBar)
    }

    private fun setupAnimator() {
        animator.duration = totalDuration.toLong()
        animator.addUpdateListener(this)
        animator.doOnEnd {
            if (progressBar.isFilled()) listener?.onBarFilled()
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        (animation.animatedValue as? Int?)?.let { progress ->
            progressBar.progress = progressBar.max * progress / ANIMATOR_MAX_VALUE
        }
    }

    private fun unFillProgressBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && animateSnaps) {
            progressBar.setProgress(0, true)
        } else {
            progressBar.progress = 0
        }
        animator.cancel()
    }

    private fun startProgressBar() {
        progressBar.progress = 0
        animator.start()
    }

    private fun pauseProgressBar() {
        animator.pause()
    }

    private fun resumeProgressBar() {
        animator.resume()
    }

    private fun fillProgressBar() {
        animator.cancel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && animateSnaps) {
            progressBar.setProgress(progressBar.max, true)
        } else {
            progressBar.progress = progressBar.max
        }
    }

    fun setCornerRadius(cornerRadius: Int) {
        progressBar.trackCornerRadius = cornerRadius.dpToPixels()
    }

    fun setDuration(duration: Int) {
        progressBar.max = duration
        animator.duration = duration.toLong()
        if (state == FILLED) {
            fillProgressBar()
        }
    }

    fun setBarTrackColor(@ColorInt barTrackColor: Int?) {
        barTrackColor?.let { progressBar.trackColor = it }
    }

    fun setBarIndicatorColor(@ColorInt barIndicatorColor: Int?) {
        barIndicatorColor?.let { progressBar.setIndicatorColor(it) }
    }
}
