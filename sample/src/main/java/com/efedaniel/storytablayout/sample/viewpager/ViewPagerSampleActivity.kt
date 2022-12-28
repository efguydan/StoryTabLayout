package com.efedaniel.storytablayout.sample.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efedaniel.storytablayout.sample.databinding.ActivityViewPagerSampleBinding

class ViewPagerSampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.storyTabLayout.run {
            setupWithViewPager(binding.viewPager)
            start()
        }
    }
}
