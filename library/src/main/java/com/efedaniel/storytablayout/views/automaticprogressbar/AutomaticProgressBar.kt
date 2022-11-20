package com.efedaniel.storytablayout.views.automaticprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.progressindicator.LinearProgressIndicator

internal class AutomaticProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val totalDuration: Int = 5000
) : FrameLayout(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {

    private val progressBar = LinearProgressIndicator(context, attrs, defStyleAttr)

    private val animator by lazy {
        ValueAnimator.ofInt(0, progressBar.max)
    }

    private var animationEndListener: (() -> Unit)? = null

    init {
        setupProgressBar()
        setupAnimator()
    }

    private fun setupProgressBar() {
        addView(progressBar)
        progressBar.max = totalDuration
    }

    private fun setupAnimator() {
        animator.addUpdateListener(this)
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        (animation.animatedValue as? Int?)?.let {
            progressBar.progress = it
        }
    }

    override fun onAnimationEnd() {
        super.onAnimationEnd()
        animationEndListener?.invoke()
    }
}
