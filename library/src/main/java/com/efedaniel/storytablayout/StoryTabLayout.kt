package com.efedaniel.storytablayout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.efedaniel.storytablayout.setup.STLSetup
import com.efedaniel.storytablayout.setup.SetupType

class StoryTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), STLSetup {

    private var owner: SetupType? = null

    override fun setup(type: SetupType) {
        owner = type
        invalidate()
    }
}
