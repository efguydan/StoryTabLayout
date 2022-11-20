package com.efedaniel.storytablayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.efedaniel.storytablayout.setup.STLSetup
import com.efedaniel.storytablayout.setup.SetupType
import com.efedaniel.storytablayout.views.automaticprogressbar.AutomaticProgressBar
import com.efedaniel.storytablayout.views.divider.Divider

class StoryTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), STLSetup {

    private var setupType: SetupType? = null

    private val numberOfTabs: Int
        get() = setupType?.getNumberOfTabs() ?: 0

    init {
        setupLayout()
    }

    private fun setupLayout() {
        dividerDrawable = Divider(sizeInDp = 4)
        showDividers = SHOW_DIVIDER_MIDDLE
    }

    override fun setup(type: SetupType) {
        setupType = type
        setupProgressBars()
    }

    private fun setupProgressBars() {
        dividerPadding
        repeat(numberOfTabs) {
            val progressBar = AutomaticProgressBar(context)
            addView(progressBar)
            progressBar.updateLayoutParams<LayoutParams> {
                weight = 1f
            }
        }
    }
}
