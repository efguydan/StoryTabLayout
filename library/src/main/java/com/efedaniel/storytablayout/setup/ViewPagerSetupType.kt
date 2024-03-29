/**
 * Copyright (c) 2023 EfeDaniel.
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
package com.efedaniel.storytablayout.setup

import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

internal class ViewPagerSetupType(private val viewPager: ViewPager) : SetupType, OnPageChangeListener {

    init {
        viewPager.addOnPageChangeListener(this)
    }

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int = viewPager.adapter?.count ?: 0

    override fun onCurrentBarFilled(nextIndex: Int) {
        viewPager.setCurrentItem(nextIndex, true)
    }

    override fun getInitialPage(): Int {
        return viewPager.currentItem
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // Do Nothing
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.invoke(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        // Do Nothing
    }
}
