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
package com.efedaniel.storytablayout.setup.setuptype

import androidx.viewpager2.widget.ViewPager2
import com.efedaniel.storytablayout.extensions.moveToNextPage

internal class ViewPager2SetupType(private val viewPager2: ViewPager2) :
    SetupType, ViewPager2.OnPageChangeCallback() {

    init {
        viewPager2.registerOnPageChangeCallback(this)
    }

    override var onPageSelected: ((Int) -> Unit)? = null

    override fun getNumberOfTabs(): Int = viewPager2.adapter?.itemCount ?: 0

    override fun onCurrentBarFilled(nextIndex: Int) {
        viewPager2.moveToNextPage()
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.invoke(position)
    }
}
