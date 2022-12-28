package com.efedaniel.storytablayout.sample.numberoftabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efedaniel.storytablayout.listener.StoryTabLayoutListener
import com.efedaniel.storytablayout.sample.R
import com.efedaniel.storytablayout.sample.databinding.ActivityNumberOfTabsSampleBinding
import com.efedaniel.storytablayout.sample.databinding.ActivityViewPagerSampleBinding

class NumberOfTabsSampleActivity : AppCompatActivity(), StoryTabLayoutListener {

    private lateinit var binding: ActivityNumberOfTabsSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberOfTabsSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupView()
    }

    private fun setupView() {
        binding.storyTabLayout.let {
            it.setupWithNumberOfTabs(10)
            it.addListener(this)
            it.start()
        }
    }

    override fun onNewPage(index: Int) {
        binding.textView.text = index.toString()
    }
}
