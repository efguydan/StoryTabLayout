package com.efedaniel.storytablayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import com.efedaniel.storytablayout.controls.STLControls
import com.efedaniel.storytablayout.setup.STLSetup
import com.efedaniel.storytablayout.setup.setuptype.SetupType
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBar
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.FILLED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.STARTED
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBarState.UNFILLED
import com.efedaniel.storytablayout.views.divider.Divider

class StoryTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), STLSetup, STLControls {

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
        dividerDrawable = Divider(sizeInDp = 4)
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    // endregion

    // region Setup

    override fun setup(type: SetupType) {
        setupType = type
        setupProgressBars()
    }

    private fun setupProgressBars() {
        removeAllViews()
        repeat(numberOfTabs) { addProgressBar() }
    }

    private fun addProgressBar() {
        val progressBar = AutomaticProgressBar(context)
        addView(progressBar)
        progressBar.updateLayoutParams<LayoutParams> { weight = 1f }
    }

    // endregion

    // region Controls

    override fun start() {
        onNewPageSelected(currentPage)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun restart() {
        TODO("Not yet implemented")
    }

    override fun restartCurrentTab() {
        TODO("Not yet implemented")
    }

    override fun jumpToPage(index: Int): Boolean {
        TODO("Not yet implemented")
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

    // endregion
}
