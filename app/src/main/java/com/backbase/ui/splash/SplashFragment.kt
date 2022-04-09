package com.backbase.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.backbase.databinding.SplashFragmentBinding
import com.backbase.ui.base.BaseFragment

class SplashFragment : BaseFragment<SplashFragmentBinding>(SplashFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbSplash.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionGoToSearchFragment())
        }
    }

}
