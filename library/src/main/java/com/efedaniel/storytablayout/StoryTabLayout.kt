/**
 * Copyright (c) 2022 EfeDaniel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.efedaniel.storytablayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import com.efedaniel.storytablayout.controls.STLControls
import com.efedaniel.storytablayout.extensions.dpToPixels
import com.efedaniel.storytablayout.listener.StoryTabLayoutListener
import com.efedaniel.storytablayout.setup.STLSetup
import com.efedaniel.storytablayout.setup.setuptype.SetupType
import com.efedaniel.storytablayout.utils.Defaults
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBar
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarListener
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.FILLED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.PAUSED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.RESUMED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.STARTED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.UNFILLED
import com.efedaniel.storytablayout.views.divider.Divider
import kotlin.math.roundToInt

class StoryTabLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), AutomaticProgressBarListener, STLSetup, STLControls {

    // region Exposed Variables

    var barSpacing: Int = Defaults.TabLayout.BAR_SPACING.dpToPixels()
        set(value) {
            field = value
            updateLayoutBarSpacing()
        }
    var barCornerRadius: Int = Defaults.Bar.CORNER_RADIUS.dpToPixels()
        set(value) {
            field = value
            updateBarCornerRadius()
        }
    var barDurationInMs: Int = Defaults.Bar.DURATION
        set(value) {
            field = value
            updateBarDuration()
        }

    @ColorInt
    var barTrackColor: Int? = null
        set(value) {
            field = value
            updateBarTrackColor()
        }

    @ColorInt
    var barIndicatorColor: Int? = null
        set(value) {
            field = value
            updateBarIndicatorColor()
        }
    var animateBarSnaps: Boolean = false
        set(value) {
            field = value
            updateBarAnimateSnaps()
        }

    // endregion

    // region Internal Variables

    private var setupType: SetupType? = null

    private val numberOfTabs: Int
        get() = setupType?.getNumberOfTabs() ?: 0

    private var currentPage = 0

    private var listener: StoryTabLayoutListener? = null

    // endregion

    // region Init

    init {
        setupLayout()
        getStyleableProperties()
    }

    private fun setupLayout() {
        updateLayoutBarSpacing()
    }

    private fun getStyleableProperties() {
        context.withStyledAttributes(attrs, R.styleable.StoryTabLayout) {
            if (hasValue(R.styleable.StoryTabLayout_barSpacing)) {
                barSpacing = getDimensionOrThrow(R.styleable.StoryTabLayout_barSpacing).roundToInt()
            }

            if (hasValue(R.styleable.StoryTabLayout_barCornerRadius)) {
                barCornerRadius =
                    getDimensionOrThrow(R.styleable.StoryTabLayout_barCornerRadius).roundToInt()
            }

            barDurationInMs = getInt(R.styleable.StoryTabLayout_barDurationInMs, barDurationInMs)

            if (hasValue(R.styleable.StoryTabLayout_barTrackColor)) {
                barTrackColor = getColorOrThrow(R.styleable.StoryTabLayout_barTrackColor)
            }

            if (hasValue(R.styleable.StoryTabLayout_barIndicatorColor)) {
                barIndicatorColor = getColorOrThrow(R.styleable.StoryTabLayout_barIndicatorColor)
            }

            animateBarSnaps = getBoolean(R.styleable.StoryTabLayout_animateBarSnaps, false)
        }
    }

    // endregion

    // region Setup

    override fun setup(type: SetupType) {
        type.onPageSelected = ::onNewPageSelected
        setupType = type
        setupProgressBars()
    }

    private fun setupProgressBars() {
        removeAllViews()
        repeat(numberOfTabs) { addProgressBar() }
        doOnLayout {
            updateBarThickness()
            updateBarCornerRadius()
        }
    }

    private fun addProgressBar() {
        val progressBar = AutomaticProgressBar(
            context = context,
            listener = this,
            totalDuration = barDurationInMs,
            cornerRadius = barCornerRadius,
            trackColor = barTrackColor,
            indicatorColor = barIndicatorColor
        )
        progressBar.animateSnaps = animateBarSnaps
        addView(progressBar)
        progressBar.updateLayoutParams<LayoutParams> { weight = 1f }
    }

    fun addListener(listener: StoryTabLayoutListener) {
        this.listener = listener
    }

    // endregion

    // region Controls

    override fun start() {
        (getChildAt(currentPage) as? AutomaticProgressBar)?.let {
            if (it.isMoving().not()) onNewPageSelected(currentPage)
        }
    }

    override fun pause() {
        (getChildAt(currentPage) as? AutomaticProgressBar)?.state = PAUSED
    }

    override fun resume() {
        (getChildAt(currentPage) as? AutomaticProgressBar)?.state = RESUMED
    }

    override fun stop(fillAllBars: Boolean) {
        children
            .map { it as? AutomaticProgressBar? }
            .forEach { it?.state = if (fillAllBars) FILLED else UNFILLED }
        currentPage = 0
    }

    override fun restartCurrentTab() {
        (getChildAt(currentPage) as? AutomaticProgressBar)?.state = STARTED
    }

    override fun jumpToPage(index: Int) {
        currentPage = index
        onNewPageSelected(currentPage)
    }

    // endregion

    // region Automatic Progress Bar Listener

    override fun onBarFilled() {
        setupType?.onCurrentBarFilled(nextIndex = currentPage + 1)
    }

    // endregion

    // region Internal Methods

    private fun onNewPageSelected(newPage: Int) {
        currentPage = newPage
        listener?.onNewPage(newPage)
        children
            .map { it as? AutomaticProgressBar? }
            .forEachIndexed { i, bar ->
                when (i) {
                    in 0 until currentPage -> bar?.state = FILLED
                    currentPage -> bar?.state = STARTED
                    else -> bar?.state = UNFILLED
                }
            }
    }

    private fun updateLayoutBarSpacing() {
        dividerDrawable = Divider(sizeInPixels = barSpacing)
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    private fun updateBarCornerRadius() {
        children.map { it as? AutomaticProgressBar? }.forEach { it?.setCornerRadius(barCornerRadius) }
    }

    private fun updateBarDuration() {
        children.map { it as? AutomaticProgressBar? }.forEach { it?.setDuration(barDurationInMs) }
    }

    private fun updateBarTrackColor() {
        children.map { it as? AutomaticProgressBar? }.forEach { it?.setBarTrackColor(barTrackColor) }
    }

    private fun updateBarIndicatorColor() {
        children
            .map { it as? AutomaticProgressBar? }
            .forEach { it?.setBarIndicatorColor(barIndicatorColor) }
    }

    private fun updateBarAnimateSnaps() {
        children.map { it as? AutomaticProgressBar? }.forEach { it?.animateSnaps = animateBarSnaps }
    }

    private fun updateBarThickness() {
        children.map { it as? AutomaticProgressBar? }.forEach { it?.setBarThickness(height) }
    }
    // endregion
}
