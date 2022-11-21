package com.efedaniel.storytablayout.views.automaticprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
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
    private val totalDuration: Int = 5000,
) : FrameLayout(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {

    private val progressBar = LinearProgressIndicator(context, attrs, defStyleAttr)

    var state: AutomaticProgressBarState = UNFILLED
        set(value) {
            field = value
            when(value) {
                UNFILLED -> unfillProgressBar()
                STARTED -> startProgressBar()
                PAUSED -> pauseProgressBar()
                RESUMED -> resumeProgressBar()
                FILLED -> fillProgressBar()
            }
        }

    private val animator by lazy {
        ValueAnimator.ofInt(0, totalDuration)
    }

    init {
        setupProgressBar()
        setupAnimator()
    }

    private fun setupProgressBar() {
        progressBar.max = totalDuration
        addView(progressBar)
    }

    private fun setupAnimator() {
        animator.duration = totalDuration.toLong()
        animator.addUpdateListener(this)
        animator.doOnEnd {
            listener?.onBarFilled()
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        (animation.animatedValue as? Int?)?.let {
            progressBar.progress = it
        }
    }

    private fun unfillProgressBar() {
        progressBar.progress = 0
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
        progressBar.progress = progressBar.max
    }
}
