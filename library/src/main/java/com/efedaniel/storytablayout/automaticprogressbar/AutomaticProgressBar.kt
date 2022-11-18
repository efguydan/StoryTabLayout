package com.efedaniel.storytablayout.automaticprogressbar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.efedaniel.storytablayout.databinding.LayoutAutomaticProgressBarBinding

internal class AutomaticProgressBar(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener {

    private val binding = LayoutAutomaticProgressBarBinding.inflate(
        LayoutInflater.from(context),
        this,
        false
    )

    private val animator by lazy {
        ValueAnimator.ofInt(0, binding.linearProgressIndicator.max)
    }

    private var animationEndListener: (() -> Unit)? = null

    init {
        setupAnimator()
    }

    private fun setupAnimator() {
        animator.addUpdateListener(this)
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animationEndListener?.invoke()
            }
        })
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        (animation.animatedValue as? Int?)?.let {
            binding.linearProgressIndicator.progress = it
        }
    }
}