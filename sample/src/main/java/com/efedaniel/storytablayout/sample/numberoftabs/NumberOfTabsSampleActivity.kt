package com.efedaniel.storytablayout.sample.numberoftabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efedaniel.storytablayout.sample.R
import com.efedaniel.storytablayout.sample.databinding.ActivityNumberOfTabsSampleBinding
import com.efedaniel.storytablayout.sample.databinding.ActivityViewPagerSampleBinding

class NumberOfTabsSampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberOfTabsSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberOfTabsSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupView()
    }

    private fun setupView() {
        binding.storyTabLayout.setupWithNumberOfTabs(10)
        binding.storyTabLayout.start()
    }
}
