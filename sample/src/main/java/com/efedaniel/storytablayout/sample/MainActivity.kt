package com.efedaniel.storytablayout.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efedaniel.storytablayout.sample.databinding.ActivityMainBinding
import com.efedaniel.storytablayout.sample.viewpager2.ViewPager2SampleActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.viewPager2.setOnClickListener {
            openActivity(ViewPager2SampleActivity::class.java)
        }
    }

    private fun openActivity(destination: Class<*>) {
        Intent(this, destination).also {
            startActivity(it)
        }
    }
}