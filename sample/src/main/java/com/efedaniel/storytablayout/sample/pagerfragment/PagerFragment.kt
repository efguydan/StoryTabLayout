package com.efedaniel.storytablayout.sample.pagerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.efedaniel.storytablayout.sample.databinding.FragmentPagerBinding
import com.efedaniel.storytablayout.sample.exts.getRandomLightColor

class PagerFragment : Fragment() {

    companion object {
        private const val NUMBER_KEY = "NUMBER_KEY"

        fun newInstance(number: Int): PagerFragment {
            val args = Bundle()
            args.putInt(NUMBER_KEY, number)
            val fragment = PagerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = arguments?.getInt(NUMBER_KEY)?.toString()
        binding.view.setBackgroundColor(getRandomLightColor())
    }
}
