package com.efedaniel.storytablayout.sample.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efedaniel.storytablayout.sample.databinding.ActivityViewPager2SampleBinding

class ViewPager2SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPager2SampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2SampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupViewPager2()
    }

    private fun setupViewPager2() {
        binding.viewPager.adapter = ViewPager2Adapter(this)
        binding.storyTabLayout.setupWithViewPager2(binding.viewPager)
        binding.storyTabLayout.start()
    }
}
