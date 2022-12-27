package com.efedaniel.storytablayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import com.efedaniel.storytablayout.controls.STLControls
import com.efedaniel.storytablayout.setup.STLSetup
import com.efedaniel.storytablayout.setup.setuptype.SetupType
import com.efedaniel.storytablayout.utils.Defaults
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBar
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarListener
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.FILLED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.PAUSED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.STARTED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.UNFILLED
import com.efedaniel.storytablayout.views.divider.Divider

class StoryTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr), AutomaticProgressBarListener, STLSetup, STLControls {

    // private val exposedVariables

    var barSpacing: Int = Defaults.TabLayout.BAR_SPACING
        set(value) {
            field = value
            setLayoutBarSpacing()
        }

    val barCornerRadius: Int = Defaults.Bar.CORNER_RADIUS
    val barDuration: Int = Defaults.Bar.DURATION
    @ColorRes val barTrackColor: Int? = null
    @ColorRes val barFillerColor: Int? = null

    // endregion

    // region Internal Variables

    private var setupType: SetupType? = null

    private val numberOfTabs: Int
        get() = setupType?.getNumberOfTabs() ?: 0

    private var currentPage = 0

    // endregion

    // region Init

    init {
        setupLayout()
    }

    private fun setupLayout() {
        setLayoutBarSpacing()
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
    }

    private fun addProgressBar() {
        val progressBar = AutomaticProgressBar(
            context = context,
            listener = this,
            totalDuration = barDuration,
            cornerRadius = barCornerRadius,
        )
        addView(progressBar)
        progressBar.updateLayoutParams<LayoutParams> { weight = 1f }
    }

    // endregion

    // region Controls

    override fun start() {
        onNewPageSelected(currentPage)
    }

    override fun pause() {
        (getChildAt(currentPage) as? AutomaticProgressBar)?.state = PAUSED
    }

    override fun stop() {
        TODO("Not yet implemented")
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
        setupType?.onCurrentBarFilled(currentPage + 1)
    }

    // endregion

    // region Internal Methods

    private fun onNewPageSelected(newPage: Int) {
        currentPage = newPage
        children.map { it as? AutomaticProgressBar? }.forEachIndexed { i, bar ->
            when(i) {
                in 0 until currentPage -> bar?.state = FILLED
                currentPage -> bar?.state = STARTED
                else -> bar?.state = UNFILLED
            }
        }
    }

    private fun setLayoutBarSpacing() {
        dividerDrawable = Divider(sizeInDp = barSpacing)
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    // endregion
}
